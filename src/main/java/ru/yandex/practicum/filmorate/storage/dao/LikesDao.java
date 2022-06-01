package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.storage.LikeStorage;

@Component
@Primary
public class LikesDao extends BaseDao implements LikeStorage {

    @Autowired
    public LikesDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void addLike(int filmId, int userId) {
        getDb().update("INSERT INTO likes (film_id, user_id) VALUES (?,?)", filmId, userId);
    }

    @Override
    public void removeLike(int filmId, int userId) {
        getDb().update("DELETE FROM likes WHERE film_id = ? AND user_id = ?", filmId, userId);
    }
}
