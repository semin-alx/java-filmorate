package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для пользователей
 */
@Service
public class UserService extends BaseService<User> {

    private UserStorage userStorage;
    private FriendStorage friendStorage;

    @Autowired
    public UserService(UserStorage storage, FriendStorage friendStorage) {
        super(storage);
        this.userStorage = storage;
        this.friendStorage = friendStorage;
    }

    public void addFriends(int userId1, int userId2) {
        userStorage.checkUserId(userId1);
        userStorage.checkUserId(userId2);
        friendStorage.addFriend(userId1, userId2);
    }

    public void removeFriends(int userId1, int userId2) {
        userStorage.checkUserId(userId1);
        userStorage.checkUserId(userId2);
        friendStorage.removeFriend(userId1, userId2);
    }

    public List<User> getFriends(int userId) {
        User user1 = userStorage.getItemById(userId);
        return user1.getFriends().stream()
                .map(n -> userStorage.getItemById(n))
                .collect(Collectors.toList());
    }

    public List<User> getCommonFriends(int userId1, int userId2) {
        User user1 = userStorage.getItemById(userId1);
        User user2 = userStorage.getItemById(userId2);
        Set<Integer> friendsOfUser2 = user2.getFriends();
        return user1.getFriends().stream()
                .filter(n -> friendsOfUser2.contains(n))
                .map(n -> userStorage.getItemById(n))
                .collect(Collectors.toList());
    }

}
