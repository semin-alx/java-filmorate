package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Данный класс представляет тип "Рейтинг Ассоциации кинокомпаний"
 */

@Data
@AllArgsConstructor
public class Mpa {
    private int id;
    private String name = "";
    private String descr = "";
}
