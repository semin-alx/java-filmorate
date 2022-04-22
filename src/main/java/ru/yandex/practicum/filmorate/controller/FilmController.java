package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@RestController
@Slf4j
public class FilmController {

    @Autowired
    FilmService filmService;

    @PostMapping(value = "/films")
    public void create(@RequestBody Film film) {
        try {
            filmService.create(film);
            log.info("Добавлен новый фильм: " + film.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка добавления фильма: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(value = "/films")
    public void update(@RequestBody Film film) {
        try {
            filmService.update(film);
            log.info("Фильм обновлен: " + film.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка обновления фильма: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping(value = "/films")
    public List<Film> getList() {
        return filmService.getItems();
    }

}
