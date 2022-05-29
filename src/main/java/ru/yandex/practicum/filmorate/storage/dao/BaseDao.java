package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import ru.yandex.practicum.filmorate.exception.ItemNotFoundException;

public class BaseDao {

    private final JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getDb() {
        return jdbcTemplate;
    }

    protected int getGeneratedKey() {
        SqlRowSet userRows = getDb().queryForRowSet("SELECT SCOPE_IDENTITY() AS key");
        userRows.next();
        return userRows.getInt("key");
    }

    protected void checkUserId(int userId) {
        SqlRowSet rows = getDb().queryForRowSet("SELECT 1 FROM user WHERE user_id = ?", userId);
        if (!rows.next()) {
            throw new ItemNotFoundException("Пользователь с указанным id не найден");
        }
    }

    protected void checkFilmId(int filmId) {
        SqlRowSet rows = getDb().queryForRowSet("SELECT 1 FROM film WHERE film_id = ?", filmId);
        if (!rows.next()) {
            throw new ItemNotFoundException("Фильм с указанным id не найден");
        }
    }


    public BaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
