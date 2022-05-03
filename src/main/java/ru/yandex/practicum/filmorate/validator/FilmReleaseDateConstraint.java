package ru.yandex.practicum.filmorate.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Реализация анотации @FilmReleaseDateConstraint для валидации
 * даты проверки релиза фильма
 * Используется в классе Film
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= FilmReleaseDateValidator.class)
public @interface FilmReleaseDateConstraint {
    String message() default "Неверная дата выпуска фильма";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
