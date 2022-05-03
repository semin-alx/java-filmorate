package ru.yandex.practicum.filmorate.model;

import lombok.*;
import ru.yandex.practicum.filmorate.validator.FilmReleaseDateConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class Film extends Item {

    @NonNull
    @NotBlank(message = "Название фильма не может быть пустым")
    private String name;

    @NonNull
    @NotBlank(message = "Описание фильма не может быть пустым")
    @Size(max = 200, message = "Описание превышает допустимую длину")
    private String description;

    @NonNull
    @FilmReleaseDateConstraint
    private LocalDate releaseDate;

    @NonNull
    @Positive(message = "Неверное значение продолжительности фильма")
    private int duration;

    private Set<Integer> likes = new HashSet<>();

    public void addLike(int userId) {
        likes.add(userId);
    }

    public void removeLike(int userId) {
        likes.remove(userId);
    }

    public Set<Integer> getLikes() {
        return likes;
    }

}
