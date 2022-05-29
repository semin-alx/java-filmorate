package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.*;
import ru.yandex.practicum.filmorate.storage.dao.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilmoRateApplicationTests {

    private final UserDao userStorage;
    private final FilmDao filmStorage;

    @Order(1)
    @Test
    public void test_user_storage_create() {

        User user1 = new User("aaa@bbb.ru", "user1",
                LocalDate.of(1985, 2, 8));

        userStorage.create(user1);

        User user2 = new User("bbb@bbb.ru", "user2",
                LocalDate.of(1995, 3, 9));

        userStorage.create(user2);

        User user3 = new User("ccc@bbb.ru", "user3",
                LocalDate.of(2005, 4, 10));

        userStorage.create(user3);

        List<User> users = userStorage.getItems();
        Assertions.assertTrue(users.size() == 3);

    }

    @Order(2)
    @Test
    public void test_user_storage_update() {

        User user3 = userStorage.getItemById(3);
        user3.setLogin("user3 updated");
        userStorage.update(user3);

        User user3u = userStorage.getItemById(3);
        Assertions.assertEquals("user3 updated", user3u.getLogin());

    }

    @Order(3)
    @Test
    public void test_user_storage_add_friend() {

        userStorage.addFriend(1, 2);
        User user1 = userStorage.getItemById(1);

        Assertions.assertEquals(1, user1.getFriends().size());
        Assertions.assertEquals(2, user1.getFriends().toArray()[0]);

    }

    @Order(4)
    @Test
    public void test_user_storage_remove_friend() {
        userStorage.removeFriend(1, 2);
        User user1 = userStorage.getItemById(1);
        Assertions.assertEquals(0, user1.getFriends().size());
    }

    @Order(5)
    @Test
    public void test_film_storage_create() {

        Film film1 = new Film("aaa",
                "aaa descr",
                LocalDate.of(2001, 4, 10),
                120);
        film1.setMpa(new Mpa(1, null, null));

        Film film2 = new Film("bbb",
                "bbb descr",
                LocalDate.of(2002, 4, 10),
                120);
        film2.setMpa(new Mpa(1, null, null));

        filmStorage.create(film1);
        filmStorage.create(film2);

        List<Film> film = filmStorage.getItems();
        Assertions.assertTrue(film.size() == 2);

    }

    @Order(6)
    @Test
    public void test_film_storage_update() {

        Film film1 = filmStorage.getItemById(1);
        film1.setName("film2 updated");
        filmStorage.update(film1);

        film1 = filmStorage.getItemById(1);
        Assertions.assertEquals("film2 updated", film1.getName());

    }

    @Order(7)
    @Test
    public void test_film_storage_add_like() {

        filmStorage.addLike(2, 1);
        filmStorage.addLike(2, 2);
        filmStorage.addLike(2, 3);

        Film film2 = filmStorage.getItemById(2);
        Assertions.assertEquals(3, film2.getLikes().size());

    }

    @Order(8)
    @Test
    public void test_film_storage_remove_like() {
        filmStorage.removeLike(2, 3);
        Film film2 = filmStorage.getItemById(2);
        Assertions.assertEquals(2, film2.getLikes().size());
    }

}
