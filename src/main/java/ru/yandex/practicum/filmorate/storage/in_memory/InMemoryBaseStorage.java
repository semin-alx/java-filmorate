package ru.yandex.practicum.filmorate.storage.in_memory;

import ru.yandex.practicum.filmorate.exception.ItemNotFoundException;
import ru.yandex.practicum.filmorate.model.Item;
import ru.yandex.practicum.filmorate.storage.BaseStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Реализация хранилища в оперативной памяти для объектов, класс которых производный от Item
 * Основные функции: хранение и управление уникальными идентификаторами хранимых объектов (id)
 * @param <T> Тип хранимых объектов
 */
public class InMemoryBaseStorage<T extends Item> implements BaseStorage<T> {

    private Map<Integer, T> items = new HashMap<>();
    private int idCounter = 0;

    @Override
    public void create(T item) {
        idCounter++;
        item.setId(idCounter);
        items.put(idCounter, item);
    }

    @Override
    public void update(T item) {

        if (!items.containsKey(item.getId())) {
            throw new ItemNotFoundException("Элемент не найден");
        }

        items.put(item.getId(), item);
    }

    @Override
    public List<T> getItems() {
        return items.entrySet().stream()
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public T getItemById(int id) {

        T item = items.get(id);

        if (item == null) {
            throw new ItemNotFoundException("Объект с указанным id не найден");
        }

        return item;
    }
}
