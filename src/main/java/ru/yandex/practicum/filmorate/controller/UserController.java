package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public User create(@Valid @RequestBody User user) {
        try {
            userService.create(user);
            log.info("Добавлен новый пользователь: " + user.toString());
            return user;
        } catch (RuntimeException e) {
            log.warn("Ошибка добавления пользователя: " + e.getMessage());
            throw e;
        }

    }

    @PutMapping(value = "/users")
    public void update(@RequestBody User user) {
        try {
            userService.update(user);
            log.info("Пользователь обновлен: " + user.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка обновления пользователя: " + e.getMessage());
            throw e;
        }
    }

    @GetMapping(value = "/users")
    public List<User> getList() {
        return userService.getItems();
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getItemById(id);
    }

    // В Т.З. метод PUT, в тестах POST
    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriends(@PathVariable int id, @PathVariable int friendId) {
        try {
            userService.addFriends(id, friendId);
            log.info("Пользователи с id:" + id + " и " + friendId + " теперь друзья");
        } catch (RuntimeException e) {
            log.warn("Ошибка добавления в друзья: " + e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriends(@PathVariable int id, @PathVariable int friendId) {
        try {
            userService.removeFriends(id, friendId);
            log.info("Пользователи с id:" + id + " и " + friendId + " теперь не друзья");
        } catch (RuntimeException e) {
            log.warn("Ошибка удаления друзей: " + e.getMessage());
            throw e;
        }
    }

    @GetMapping(value = "/users/{id}/friends")
    public List<User> getFriends(@PathVariable int id) {
        return userService.getFriends(id);
    }

    @GetMapping(value = "/users/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable int id, @PathVariable int otherId) {
        return userService.getCommonFriends(id, otherId);
    }

}
