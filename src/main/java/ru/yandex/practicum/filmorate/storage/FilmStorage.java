package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

/**
 * Интерфейс хранилища для объектов Film
 */
public interface FilmStorage extends BaseStorage<Film> {
    void addLike(int filmId, int userId);
    void removeLike(int filmId, int userId);
    List<Film> getTopFilms(int count);
}
