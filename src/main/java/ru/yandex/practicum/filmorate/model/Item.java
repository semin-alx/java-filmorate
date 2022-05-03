package ru.yandex.practicum.filmorate.model;

import lombok.Data;

/**
 * Общий класс для Film и User
 * Основное назначение - наличие уникального идентификатора
 */
@Data
public class Item {
    private int id;
}
