package ru.yandex.practicum.filmorate.model;

import lombok.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Film extends Item {

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private LocalDate releaseDate;

    @NonNull
    private int duration;

    // Указываем Lombok не создавать на константы геттеры, иначе они попадут в JSON
    @Getter(AccessLevel.NONE)
    private final int MAX_DESCR_LEN = 200;

    // Указываем Lombok не создавать на константы геттеры, иначе они попадут в JSON
    @Getter(AccessLevel.NONE)
    private final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    @Override
    public void validate() {
        super.validate();

        if (name.isEmpty()) {
            throw new ValidationException("Не указано название фильма");
        }

        if (description.length() > MAX_DESCR_LEN) {
            throw new ValidationException("Описание превышает допустимую длину");
        }

        if (releaseDate.isBefore(MIN_RELEASE_DATE)) {
            throw new ValidationException("Невеная дата выпуска фильма");
        }

        if (duration < 0) {
            throw new ValidationException("Неверное значение продолжительности фильма");
        }

    }
}
