package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import java.util.List;

/**
 * Сервис для фильмов
 */
@Service
public class FilmService extends BaseService<Film> {

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        super(filmStorage);
    }

    public void like(int filmId, int userId) {
        ((FilmStorage)getStorage()).addLike(filmId, userId);
    }

    public void removeLike(int filmId, int userId) {
        ((FilmStorage)getStorage()).removeLike(filmId, userId);
    }

    public List<Film> getTopFilms(int count) {
        return ((FilmStorage)getStorage()).getTopFilms(count);
    }

}
