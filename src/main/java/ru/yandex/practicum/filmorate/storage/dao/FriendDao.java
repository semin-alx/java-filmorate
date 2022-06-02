package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.storage.FriendStorage;

@Component
@Primary
public class FriendDao extends BaseDao implements FriendStorage {

    @Autowired
    public FriendDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void addFriend(int userId, int friendId) {
        getDb().update("INSERT INTO friend (user_id1, user_id2) VALUES (?,?)", userId, friendId);
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        getDb().update("DELETE FROM friend WHERE user_id1 = ? AND user_id2 = ?",
                userId, friendId);
    }
}
