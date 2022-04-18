package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilmManager {

    private final int MAX_DESCR_LEN = 200;
    private final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    private Map<Integer, Film> films = new HashMap<>();
    private int idCounter = 0;

    private void validateFilmName(String filmName) {
        if (filmName.isEmpty()) {
            throw new ValidationException("Не указано название фильма");
        }
    }

    private void validateFilmDescr(String filmDsecr) {
        if (filmDsecr.length() > MAX_DESCR_LEN) {
            throw new ValidationException("Описание превышает допустимую длину");
        }
    }

    private void validateReleaseDate(LocalDate date) {
        if (date.isBefore(MIN_RELEASE_DATE)) {
            throw new ValidationException("Невеная дата выпуска фильма");
        }
    }

    private void validateDuration(int duration) {
        if (duration < 0) {
            throw new ValidationException("Неверное значение продолжительности фильма");
        }
    }

    private void validate(Film film) {
        validateFilmName(film.getName());
        validateFilmDescr(film.getDescription());
        validateReleaseDate(film.getReleaseDate());
        validateDuration(film.getDuration());
    }

    public void create(Film film) {
        validate(film);
        idCounter++;
        film.setId(idCounter);
        films.put(idCounter, film);
    }

    public void update(Film film) {

        if (!films.containsKey(film.getId())) {
            throw new FilmNotFoundException("Фильм не найден");
        }

        validate(film);
        films.put(film.getId(), film);

    }

    public List<Film> getFilms() {
        return films.entrySet().stream()
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }

    public void clear() {
        films.clear();
        idCounter = 0;
    }

}
