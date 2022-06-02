package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Данный класс представляет тип "Жанр фильма"
 */
@Data
@AllArgsConstructor
public class Genre {
    private int id;
    private String name;
}
