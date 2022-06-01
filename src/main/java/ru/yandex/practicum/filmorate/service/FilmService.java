package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.LikeStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

/**
 * Сервис для фильмов
 */
@Service
public class FilmService extends BaseService<Film> {

    private UserStorage userStorage;
    private FilmStorage filmStorage;
    private LikeStorage likeStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage, LikeStorage likeStorage) {
        super(filmStorage);
        this.userStorage = userStorage;
        this.filmStorage = filmStorage;
        this.likeStorage = likeStorage;
    }

    public void like(int filmId, int userId) {
        filmStorage.checkFilmId(filmId);
        userStorage.checkUserId(userId);
        likeStorage.addLike(filmId, userId);
    }

    public void removeLike(int filmId, int userId) {
        filmStorage.checkFilmId(filmId);
        userStorage.checkUserId(userId);
        likeStorage.removeLike(filmId, userId);
    }

    public List<Film> getTopFilms(int count) {
        return filmStorage.getTopFilms(count);
    }

}
