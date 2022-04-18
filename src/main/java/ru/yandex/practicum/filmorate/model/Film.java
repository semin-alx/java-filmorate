package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Film {

    private int id = 0;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private LocalDate releaseDate;

    @NonNull
    private int duration;

}
