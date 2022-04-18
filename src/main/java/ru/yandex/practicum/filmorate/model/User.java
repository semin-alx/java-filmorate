package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class User {
    private int id;

    @NonNull
    private String email;

    @NonNull
    private String login;

    private String name;

    @NonNull
    private LocalDate birthday;

}
