package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Item;

import java.util.List;

/**
 * Базовый интерфейс для работы с любым хранилищем объектов T
 * @param <T> Класс хранимых объектов
 */
public interface BaseStorage<T extends Item> {

    void create(T item);

    void update(T item);

    List<T> getItems();

    T getItemById(int id);

}

