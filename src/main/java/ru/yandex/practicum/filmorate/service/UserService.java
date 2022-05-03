package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для пользователей
 */
@Service
public class UserService extends BaseService<User> {

    public UserService(BaseStorage<User> storage) {
        super(storage);
    }

    public void addFriends(int userId1, int userId2) {
        User user1 = getStorage().getItemById(userId1);
        User user2 = getStorage().getItemById(userId2);
        user1.addFriend(userId2);
        user2.addFriend(userId1);
    }

    public void removeFriends(int userId1, int userId2) {
        User user1 = getStorage().getItemById(userId1);
        User user2 = getStorage().getItemById(userId2);
        user1.removeFriend(userId2);
        user2.removeFriend(userId1);
    }

    public List<User> getFriends(int userId) {
        User user1 = getStorage().getItemById(userId);
        return user1.getFriends().stream()
                .map(n -> getStorage().getItemById(n))
                .collect(Collectors.toList());
    }

    public List<User> getCommonFriends(int userId1, int userId2) {
        User user1 = getStorage().getItemById(userId1);
        User user2 = getStorage().getItemById(userId2);
        Set<Integer> friendsOfUser2 = user2.getFriends();
        return user1.getFriends().stream()
                .filter(n -> friendsOfUser2.contains(n))
                .map(n -> getStorage().getItemById(n))
                .collect(Collectors.toList());
    }

}
