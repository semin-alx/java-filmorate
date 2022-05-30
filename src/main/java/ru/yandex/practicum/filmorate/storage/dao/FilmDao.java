package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ItemNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@Primary
public class FilmDao extends BaseDao implements FilmStorage {

    private void updateGenreList(Film film) {
        getDb().update("DELETE FROM film_genre WHERE film_id=?", film.getId());
        film.getGenreList().stream().forEach(new Consumer<Genre>() {
            @Override
            public void accept(Genre genre) {
                getDb().update("INSERT INTO film_genre (film_id, genre_id) VALUES (?, ?)",
                        film.getId(), genre.getId());
            }
        });
    }

    private Mpa getMpa(int id) {

        SqlRowSet rows = getDb().queryForRowSet("SELECT * FROM rating_mpa WHERE mpa_id = ?", id);
        if (rows.next()) {
            return new Mpa(rows.getInt("mpa_id"),
                           rows.getString("name"),
                           rows.getString("descr"));
        } else {
            throw new ItemNotFoundException("Рейтинг фильма с указанным id не найден");
        }

    }

    private void loadGenre(Film film) {

        List<Genre> genreList = getDb().query("SELECT g.* FROM film_genre fg " +
                "INNER JOIN genre g ON g.genre_id = fg.genre_id " +
                "WHERE film_id = ?",
                (rs, n) -> new Genre(rs.getInt("genre_id"),
                                     rs.getString("name")), film.getId());

        film.setGenreList(genreList);

    }

    private void loadMpa(Film film) {
        film.setMpa(getMpa(film.getMpa().getId()));
    }

    private void loadLikes(Film film) {
        List<Integer> likes = getDb().query("SELECT user_id FROM likes WHERE film_id = ?",
                (rs, n) -> rs.getInt("user_id"), film.getId());
        film.setLikes(likes.stream().collect(Collectors.toSet()));
    }

    @Autowired
    public FilmDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void create(Film item) {

        getDb().update("INSERT INTO film (name, descr, release_date, duration, mpa_id) " +
                "VALUES (?,?,?,?,?)", item.getName(), item.getDescription(), item.getReleaseDate(),
                item.getDuration(), item.getMpa().getId());

        item.setId(getGeneratedKey());
        updateGenreList(item);
        loadMpa(item);

    }

    @Override
    public void update(Film item) {

        checkFilmId(item.getId());

        getDb().update("UPDATE film SET name=?, descr=?, release_date=?, duration=?, " +
                "mpa_id=? WHERE film_id=?", item.getName(), item.getDescription(),
                item.getReleaseDate(), item.getDuration(), item.getMpa().getId(), item.getId());

        updateGenreList(item);

        // В тестах передается mpa {"id":5}
        // А потом ожидают pm.expect(jsonData.mpa.name).not.null;
        // Перезапрашиваем информацию по Mpa
        loadMpa(item);

    }

    @Override
    public List<Film> getItems() {
        return getDb().query("SELECT film_id FROM film",
                (rs, n) -> getItemById(rs.getInt("film_id")));
    }

    @Override
    public Film getItemById(int id) {

        SqlRowSet rows = getDb().queryForRowSet("SELECT * FROM film WHERE film_id = ?", id);

        if (rows.next()) {

            Film film = new Film(rows.getString("name"),
                                 rows.getString("descr"),
                                 rows.getDate("release_date").toLocalDate(),
                                 rows.getInt("duration"));

            film.setId(id);
            film.setMpa(getMpa(rows.getInt("mpa_id")));
            loadGenre(film);
            loadLikes(film);

            return film;

        } else {
            throw new ItemNotFoundException("Фильм с указанным id не найден");
        }

    }

    @Override
    public void addLike(int filmId, int userId) {
        checkFilmId(filmId);
        checkUserId(userId);
        getDb().update("INSERT INTO likes (film_id, user_id) VALUES (?,?)", filmId, userId);
    }

    @Override
    public void removeLike(int filmId, int userId) {
        checkFilmId(filmId);
        checkUserId(userId);
        getDb().update("DELETE FROM likes WHERE film_id = ? AND user_id = ?", filmId, userId);
    }

    @Override
    public List<Film> getTopFilms(int count) {

        return getDb().query("SELECT film.film_id, COUNT(likes.user_id) AS qty " +
          "FROM film " +
          "LEFT JOIN likes ON likes.film_id = film.film_id " +
          "GROUP BY film.film_id " +
          "ORDER BY qty DESC " +
          "LIMIT ?",
          (rs, n) -> getItemById(rs.getInt("film_id")), count);

    }
}
