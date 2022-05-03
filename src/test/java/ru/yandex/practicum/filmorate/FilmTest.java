package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FilmTest {

    private static Validator validator;

    private void validate(Film film, String expectMessage) {
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        //System.out.println(violations);
        assertEquals(1, violations.size());
        assertEquals(expectMessage, violations.iterator().next().getMessage());
    }

    @BeforeAll
    public static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void check_film_empty_name() {

        Film film = new Film("",
                "vvvv",
                LocalDate.of(1974, 9, 14),
                100);

        validate(film, "Название фильма не может быть пустым");

    }

    @Test
    void check_film_descr_length201() {

        Film film = new Film("Интерстеллар",
                String.join("", Collections.nCopies(201, "A")),
                LocalDate.of(1974, 9, 14),
                100);

        validate(film, "Описание превышает допустимую длину");

    }

    @Test
    void check_film_descr_length250() {

        Film film = new Film("Интерстеллар",
                String.join("", Collections.nCopies(250, "A")),
                LocalDate.of(1974, 9, 14),
                100);

        validate(film, "Описание превышает допустимую длину");

    }

    @Test
    void check_film_descr_length200() {

        Film film = new Film("Интерстеллар",
                String.join("", Collections.nCopies(200, "A")),
                LocalDate.of(1974, 9, 14),
                100);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());

    }

    @Test
    void check_film_descr_length150() {
        Film film = new Film("Интерстеллар",
                String.join("", Collections.nCopies(150, "A")),
                LocalDate.of(1974, 9, 14),
                100);

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());

    }

    @Test
    void check_film_release_date_1() {
        Film film = new Film("Интерстеллар", "bbbbb",
                LocalDate.of(1974, 9, 14),
                100);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void check_film_release_date_2() {
        Film film = new Film("Интерстеллар", "bbbb",
                LocalDate.of(1895, 12, 28),
                100);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void check_film_release_date_3() {

        Film film = new Film("Интерстеллар", "nnn",
                LocalDate.of(1895, 12, 27),
                100);

        validate(film, "Неверная дата выпуска фильма");

    }

    @Test
    void check_film_release_date_4() {

        Film film = new Film("Интерстеллар", "тт",
                LocalDate.of(1885, 1, 1),
                100);

        validate(film, "Неверная дата выпуска фильма");

    }

    @Test
    void check_film_duration_1() {
        Film film = new Film("Интерстеллар", "bb",
                LocalDate.of(1895, 12, 28),
                0);
        validate(film, "Неверное значение продолжительности фильма");
    }

    @Test
    void check_film_duration_2() {

        Film film = new Film("Интерстеллар", "bb",
                LocalDate.of(1974, 9, 14),
                -1);

        validate(film, "Неверное значение продолжительности фильма");

    }

    @Test
    void check_film_duration_3() {

        Film film = new Film("Интерстеллар", "",
                LocalDate.of(1974, 9, 14),
                -100);

        validate(film, "Неверное значение продолжительности фильма");

    }

}
