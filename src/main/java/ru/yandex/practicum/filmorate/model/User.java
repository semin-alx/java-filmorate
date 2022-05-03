package ru.yandex.practicum.filmorate.model;

import lombok.*;
import ru.yandex.practicum.filmorate.exception.FieldValidationException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@RequiredArgsConstructor
public class User extends Item {

    @NonNull
    @NotBlank(message = "Email пользователя не должен быть пустым")
    @Email(message = "Email пользователя указан неверно")
    private String email;

    @NonNull
    @NotBlank(message = "Не указан логин пользователя")
    @Pattern(message = "Логин пользователя не может содержать пробелы", regexp = "\\S*")
    private String login;

    private String name;

    @NonNull
    @Past(message = "Неверная дата рождения")
    private LocalDate birthday;

    private Set<Integer> friends = new HashSet<>();

    public void addFriend(int friendId) {
        friends.add(friendId);
    }

    public void removeFriend(int friendId) {
        friends.remove(friendId);
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public String getName() {

        if ((name == null) || (name.isEmpty())) {
            return login;
        } else {
            return name;
        }

    }

}
