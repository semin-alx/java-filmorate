package ru.yandex.practicum.filmorate.storage.in_memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.LikeStorage;
import ru.yandex.practicum.filmorate.model.Film;

@Component
public class InMemoryLikeStorage implements LikeStorage {

    private FilmStorage filmStorage;

    @Autowired
    public InMemoryLikeStorage(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public void addLike(int filmId, int userId) {
        Film film = filmStorage.getItemById(filmId);
        film.addLike(userId);
    }

    @Override
    public void removeLike(int filmId, int userId) {
        Film film = filmStorage.getItemById(filmId);
        film.removeLike(userId);
    }

}
