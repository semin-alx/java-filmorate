package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class FilmController {

    FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        try {
            filmService.create(film);
            log.info("Добавлен новый фильм: " + film.toString());
            return film;
        } catch (RuntimeException e) {
            log.warn("Ошибка добавления фильма: " + e.getMessage());
            throw e;
        }
    }

    @PutMapping(value = "/films")
    public void update(@Valid @RequestBody Film film) {
        try {
            filmService.update(film);
            log.info("Фильм обновлен: " + film.toString());
        } catch (RuntimeException e) {
            log.warn("Ошибка обновления фильма: " + e.getMessage());
            throw e;
        }
    }

    @GetMapping(value = "/films")
    public List<Film> getList() {
        return filmService.getItems();
    }

    @GetMapping(value = "/films/{id}")
    public Film getUserById(@PathVariable int id) {
        return filmService.getItemById(id);
    }

    @PutMapping(value = "/films/{id}/like/{userId}")
    public void like(@PathVariable int id, @PathVariable int userId) {
        filmService.like(id, userId);
    }

    @DeleteMapping(value = "/films/{id}/like/{userId}")
    public void removeLike(@PathVariable int id, @PathVariable int userId) {
        filmService.removeLike(id, userId);
    }

    // /films/popular?count={count}
    @GetMapping(value = "/films/popular")
    public List<Film> getTopFilms(@RequestParam(defaultValue = "10") int count) {
        return filmService.getTopFilms(count);
    }

}
