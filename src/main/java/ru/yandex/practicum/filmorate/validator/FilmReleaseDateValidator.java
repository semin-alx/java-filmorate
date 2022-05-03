package ru.yandex.practicum.filmorate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Реализация проверки даты релиза фильма для анотации @FilmReleaseDateConstraint
 */
public class FilmReleaseDateValidator implements ConstraintValidator<FilmReleaseDateConstraint, LocalDate> {

    private static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    @Override
    public boolean isValid(LocalDate releaseDate, ConstraintValidatorContext constraintValidatorContext) {
        return (!releaseDate.isBefore(MIN_RELEASE_DATE));
    }

}
