package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для пользователей
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    public UserService(UserStorage storage) {
        super(storage);
    }

    public void addFriends(int userId1, int userId2) {
        ((UserStorage)getStorage()).addFriend(userId1, userId2);
    }

    public void removeFriends(int userId1, int userId2) {
        ((UserStorage)getStorage()).removeFriend(userId1, userId2);
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
