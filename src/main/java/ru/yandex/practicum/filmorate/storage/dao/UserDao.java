package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ItemNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Component
@Primary
public class UserDao extends BaseDao implements UserStorage {

    private void loadFriends(int id, User user) {
        List<Integer> friends = getDb().query("SELECT user_id2 FROM friend WHERE user_id1 = ?",
                (rs, n) -> rs.getInt("user_id2"), id);
        friends.stream().forEach(n -> user.addFriend(n));
    }

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void create(User item) {
        getDb().update("INSERT INTO user (login, name, email, birthday) VALUES (?,?,?,?)",
                item.getLogin(), item.getName(), item.getEmail(), item.getBirthday());
        item.setId(getGeneratedKey());
    }

    @Override
    public void update(User item) {
        checkUserId(item.getId());
        getDb().update("UPDATE user SET login = ?, name = ?, " +
                        "email = ?, birthday = ? WHERE user_id = ?",
                item.getLogin(), item.getName(), item.getEmail(), item.getBirthday(),
                item.getId());
    }

    @Override
    public List<User> getItems() {
        return getDb().query("SELECT user_id FROM user",
                (rs, n) -> getItemById(rs.getInt("user_id")));
    }

    @Override
    public User getItemById(int id) {

        SqlRowSet userRows = getDb().queryForRowSet("SELECT * FROM user WHERE user_id = ?", id);
        if (userRows.next()) {

           User user = new User(userRows.getString("email"),
                                userRows.getString("login"),
                                userRows.getDate("birthday").toLocalDate());
           user.setId(userRows.getInt("user_id"));
           user.setName(userRows.getString("name"));

           loadFriends(id, user);

           return user;

        } else {
            throw new ItemNotFoundException("Пользователь с указанным id не найден");
        }

    }

    @Override
    public void addFriend(int userId, int friendId) {
       checkUserId(userId);
       checkUserId(friendId);
       getDb().update("INSERT INTO friend (user_id1, user_id2) VALUES (?,?)", userId, friendId);
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        checkUserId(userId);
        checkUserId(friendId);
        getDb().update("DELETE FROM friend WHERE user_id1 = ? AND user_id2 = ?",
                userId, friendId);
    }

}
