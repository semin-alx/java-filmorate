package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

/**
 * Интерфейс хранилища для объектов User
 * Просто наследуем все методы из BaseStorage
 */
public interface UserStorage extends BaseStorage<User> {
    void checkUserId(int userId);
}
