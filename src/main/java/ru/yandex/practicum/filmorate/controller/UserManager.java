package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserManager {

    private Map<Integer, User> users = new HashMap<>();
    private int idCounter = 0;

    private void validateEmail(String email) {
        if (!email.contains("@")) {
            throw new ValidationException("Неверный почтовый адрес");
        }
    }

    private void validateLogin(String login) {
        if (login.trim().isEmpty()) {
            throw new ValidationException("Логин не указан или содержит одни пробелы");
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Неверная дата рождения");
        }
    }

    private void validate(User user) {

        validateEmail(user.getEmail());
        validateLogin(user.getLogin());
        validateBirthDate(user.getBirthday());

        if ((user.getName() == null)
            || (user.getName().isEmpty())) {
            user.setName(user.getLogin());
        }

    }

    public void create(User user) {
        validate(user);
        idCounter++;
        user.setId(idCounter);
        users.put(idCounter, user);
    }

    public void update(User user) {

        if (!users.containsKey(user.getId())) {
            throw new UserNotFoundException("Пользователь не найден");
        }

        validate(user);
        users.put(user.getId(), user);

    }

    public List<User> getUsers() {
        return users.entrySet().stream()
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

    public void clear() {
        users.clear();
        idCounter = 0;
    }

}
