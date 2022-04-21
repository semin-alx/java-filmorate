package ru.yandex.practicum.filmorate.model;

import lombok.Data;

/**
 * Общий класс для Film и User
 * Наличие метода validate позволяет нам в наследниках написать проверку данных
 * И при этом создать универсальный менеджер, который будет работать с любыми
 * классами производными от Item
 */
@Data
public class Item {
    private int id;
    public void validate() {}
}
