package ru.yandex.practicum.filmorate.storage.in_memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

@Component
public class InMemoryFriendStorage implements FriendStorage {

    private UserStorage userStorage;

    @Autowired
    public InMemoryFriendStorage(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void addFriend(int userId, int friendId) {
        User user = userStorage.getItemById(userId);
        user.addFriend(userId);
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        User user = userStorage.getItemById(userId);
        user.removeFriend(friendId);
    }
}
