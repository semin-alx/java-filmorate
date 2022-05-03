package ru.yandex.practicum.filmorate.storage.in_memory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

/**
 * Хранилище в оперативной памяти для фильмов
 */
@Component
public class InMemoryFilmStorage extends InMemoryBaseStorage<Film> implements FilmStorage {
}
