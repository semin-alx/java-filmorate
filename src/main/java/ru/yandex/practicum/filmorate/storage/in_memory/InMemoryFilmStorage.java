package ru.yandex.practicum.filmorate.storage.in_memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Хранилище в оперативной памяти для фильмов
 */
@Component
public class InMemoryFilmStorage extends InMemoryBaseStorage<Film> implements FilmStorage {

    @Override
    public List<Film> getTopFilms(int count) {
        return getItems().stream()
                .sorted((o1, o2) -> o2.getLikes().size() - o1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public void checkFilmId(int filmId) {
        getItemById(filmId);
    }

}
