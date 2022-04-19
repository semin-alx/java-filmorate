package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    UserManager manager = new UserManager();

    @PostMapping(value = "/users")
    public void create(@RequestBody User user) {
        try {
            manager.create(user);
            log.info("Добавлен новый пользователь: " + user.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка добавления пользователя: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PutMapping(value = "/users")
    public void update(@RequestBody User user) {
        try {
            manager.update(user);
            log.info("Пользователь обновлен: " + user.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка обновления пользователя: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping(value = "/users")
    public List<User> getList() {
        return manager.getUsers();
    }


}
