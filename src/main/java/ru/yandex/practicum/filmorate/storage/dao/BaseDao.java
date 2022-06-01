package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import ru.yandex.practicum.filmorate.exception.ItemNotFoundException;

public class BaseDao {

    private final JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getDb() {
        return jdbcTemplate;
    }

    /**
     * Функция позволяет получить значение первичного ключа
     * для таблиц с автоматическим инкрементом
     * @return - значение первичного ключа
     */
    protected int getGeneratedKey() {
        SqlRowSet userRows = getDb().queryForRowSet("SELECT SCOPE_IDENTITY() AS key");
        userRows.next();
        return userRows.getInt("key");
    }

    public BaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
