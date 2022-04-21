package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Простой менеджер позволяет управлять классами производными от Items
 * На данный момент это будут классы Film и User
 * @param <T>
 */
public class ItemManager<T extends Item> {

    private Map<Integer, T> items = new HashMap<>();
    private int idCounter = 0;

    public void create(T item) {
        item.validate();
        idCounter++;
        item.setId(idCounter);
        items.put(idCounter, item);
    }

    public void update(T item) {

        if (!items.containsKey(item.getId())) {
            throw new FilmNotFoundException("Элемент не найден");
        }

        item.validate();
        items.put(item.getId(), item);

    }

    public List<T> getItems() {
        return items.entrySet().stream()
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

    public void clear() {
        items.clear();
        idCounter = 0;
    }

}
