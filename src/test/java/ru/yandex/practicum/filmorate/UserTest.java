package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserTest {

    private static Validator validator;

    private void validate(User user, String expectMessage) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
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
    void check_user_email() {

        User user = new User("aaa",
                "login",
                LocalDate.of(1985, 2, 8));

        validate(user, "Email пользователя указан неверно");

    }

    @Test
    void check_user_empty_login_1() {

        User user = new User("aaa@bbb",
                "",
                LocalDate.of(1985, 2, 8));

        validate(user, "Не указан логин пользователя");

    }

    @Test
    void check_user_empty_login_2() {

        User user = new User("aaa@bbb",
                "bbb ",
                LocalDate.of(1985, 2, 8));

        validate(user, "Логин пользователя не может содержать пробелы");

    }

    @Test
    void check_user_birthdate_1() {

        User user = new User("aaa@bbb",
                "login",
                LocalDate.now().plusDays(1));

        validate(user, "Неверная дата рождения");

    }

    @Test
    void check_user_birthdate_2() {

        User user = new User("aaa@bbb",
                "login",
                LocalDate.now().plusDays(100));

        validate(user, "Неверная дата рождения");

    }

    @Test
    void check_user_birthdate_3() {

        User user = new User("aaa@bbb",
                "Diana",
                LocalDate.of(1961, 7, 1));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());

    }

}