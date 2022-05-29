package ru.yandex.practicum.filmorate.storage.in_memory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

/**
 * Хранилище в оперативной памяти для пользователей
 */
@Component
public class InMemoryUserStorage extends InMemoryBaseStorage<User> implements UserStorage {

    @Override
    public void addFriend(int userId, int friendId) {
        User user = getItemById(userId);
        user.addFriend(friendId);
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        User user = getItemById(userId);
        user.removeFriend(friendId);
    }

}
