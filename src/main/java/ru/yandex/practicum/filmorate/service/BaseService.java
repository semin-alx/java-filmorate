package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Item;
import ru.yandex.practicum.filmorate.storage.BaseStorage;
import java.util.List;

/**
 * Базовый сервис обеспечивающий общую функциональность при работе
 * с объектами, производных от Item
 * @param <T>
 */
public class BaseService<T extends Item> {

    private BaseStorage<T> storage;

    protected BaseStorage<T> getStorage() {
        return storage;
    }

    public BaseService(BaseStorage<T> storage) {
        this.storage = storage;
    }

    public void create(T item) {
        storage.create(item);
    }

    public void update(T item) {
        storage.update(item);
    }

    public List<T> getItems() {
        return storage.getItems();
    }

    public T getItemById(int id) {
        return storage.getItemById(id);
    }

}
