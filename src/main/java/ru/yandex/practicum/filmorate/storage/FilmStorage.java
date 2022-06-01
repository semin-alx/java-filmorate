package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

/**
 * Интерфейс хранилища для объектов Film
 */
public interface FilmStorage extends BaseStorage<Film> {
    List<Film> getTopFilms(int count);
    void checkFilmId(int filmId);
}
