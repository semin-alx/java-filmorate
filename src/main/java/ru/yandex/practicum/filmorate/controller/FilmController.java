package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

@RestController
@Slf4j
public class FilmController {

    FilmManager manager = new FilmManager();

    @PostMapping(value = "/films")
    public void create(@RequestBody Film film) {
        try {
            manager.create(film);
            log.info("Добавлен новый фильм: " + film.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка добавления фильма: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/films")
    public void update(@RequestBody Film film) {
        try {
            manager.update(film);
            log.info("Фильм обновлен: " + film.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка обновления фильма: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/films")
    public List<Film> getList() {
        return manager.getFilms();
    }

}
