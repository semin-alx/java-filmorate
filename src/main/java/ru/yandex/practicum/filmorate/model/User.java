package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class User extends Item {

    @NonNull
    private String email;

    @NonNull
    private String login;

    private String name;

    @NonNull
    private LocalDate birthday;

    @Override
    public void validate() {
        super.validate();

        if (!email.contains("@")) {
            throw new ValidationException("Неверный почтовый адрес");
        }

        if (login.isEmpty()) {
            throw new ValidationException("Логин не указан");
        }

        if (login.contains(" ")) {
            throw new ValidationException("Логин не может содержать пробелы");
        }

        if (birthday.isAfter(LocalDate.now())) {
            throw new ValidationException("Неверная дата рождения");
        }

        if ((name == null)
                || (name.isEmpty())) {
            name = login;
        }

    }
}
