package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/users")
    public void create(@RequestBody User user) {
        try {
            userService.create(user);
            log.info("Добавлен новый пользователь: " + user.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка добавления пользователя: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PutMapping(value = "/users")
    public void update(@RequestBody User user) {
        try {
            userService.update(user);
            log.info("Пользователь обновлен: " + user.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка обновления пользователя: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping(value = "/users")
    public List<User> getList() {
        return userService.getItems();
    }


}
