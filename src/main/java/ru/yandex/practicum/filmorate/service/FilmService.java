package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.BaseStorage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для фильмов
 */
@Service
public class FilmService extends BaseService<Film> {

    private BaseStorage<User> userStorage;

    private void checkUserId(int id) {
        userStorage.getItemById(id); // Если такого id нет, будет исключение
    }

    public FilmService(BaseStorage<Film> filmStorage, BaseStorage<User> userStorage) {
        super(filmStorage);
        this.userStorage = userStorage;
    }

    public void like(int filmId, int userId) {
        checkUserId(userId);
        Film film = getStorage().getItemById(filmId);
        film.addLike(userId);
    }

    public void removeLike(int filmId, int userId) {
        checkUserId(userId);
        Film film = getStorage().getItemById(filmId);
        film.removeLike(userId);
    }

    public List<Film> getTopFilms(int count) {
        return getStorage().getItems().stream()
                .sorted((o1, o2) -> o2.getLikes().size() - o1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }

}
