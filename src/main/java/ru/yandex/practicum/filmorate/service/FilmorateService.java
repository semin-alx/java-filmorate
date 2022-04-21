package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.Film;

/**
 * Класс с основной логикой нашего приложения
 */
@Service
public class FilmorateService {

    private ItemManager<User> userManager = new ItemManager<>();
    private ItemManager<Film> filmManager = new ItemManager<>();

    public ItemManager<User> getUserManager() {
        return userManager;
    }

    public ItemManager<Film> getFilmManager() {
        return filmManager;
    }

}
