package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ItemManager;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest
class FilmorateApplicationTests {

	private static ItemManager<User> userManager;
	private static ItemManager<Film> filmManager;

	@BeforeAll
	private static void init() {
		userManager = new ItemManager<>();
		filmManager = new ItemManager<>();
	}

	@BeforeEach
	private void clearBeforeTest() {
		userManager.clear();
		filmManager.clear();
	}

	@Test
	void check_film_empty_name() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						Film film = new Film("",
								"",
								LocalDate.of(1974, 9, 14),
								100);
						filmManager.create(film);
					}
				});

		assertEquals("Не указано название фильма", exception.getMessage());

	}

	@Test
	void check_film_descr_length201() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						Film film = new Film("Интерстеллар",
								String.join("", Collections.nCopies(201, "A")),
								LocalDate.of(1974, 9, 14),
								100);
						filmManager.create(film);
					}
				});

		assertEquals("Описание превышает допустимую длину", exception.getMessage());

	}

	@Test
	void check_film_descr_length250() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						Film film = new Film("Интерстеллар",
								String.join("", Collections.nCopies(250, "A")),
								LocalDate.of(1974, 9, 14),
								100);
						filmManager.create(film);
					}
				});

		assertEquals("Описание превышает допустимую длину", exception.getMessage());

	}

	@Test
	void check_film_descr_length200() {
		Film film = new Film("Интерстеллар",
				String.join("", Collections.nCopies(200, "A")),
				LocalDate.of(1974, 9, 14),
				100);
		filmManager.create(film);
		assertEquals(1, filmManager.getItems().size());

	}

	@Test
	void check_film_descr_length150() {
		Film film = new Film("Интерстеллар",
				String.join("", Collections.nCopies(150, "A")),
				LocalDate.of(1974, 9, 14),
				100);
		filmManager.create(film);
		assertEquals(1, filmManager.getItems().size());

	}

	@Test
	void check_film_release_date_1() {
		Film film = new Film("Интерстеллар", "",
				LocalDate.of(1974, 9, 14),
				100);
		filmManager.create(film);
		assertEquals(1, filmManager.getItems().size());
	}

	@Test
	void check_film_release_date_2() {
		Film film = new Film("Интерстеллар", "",
				LocalDate.of(1895, 12, 28),
				100);
		filmManager.create(film);
		assertEquals(1, filmManager.getItems().size());
	}

	@Test
	void check_film_release_date_3() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						Film film = new Film("Интерстеллар", "",
								LocalDate.of(1895, 12, 27),
								100);
						filmManager.create(film);
					}
				});

		assertEquals("Невеная дата выпуска фильма", exception.getMessage());

	}

	@Test
	void check_film_release_date_4() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						Film film = new Film("Интерстеллар", "",
								LocalDate.of(1885, 1, 1),
								100);
						filmManager.create(film);
					}
				});

		assertEquals("Невеная дата выпуска фильма", exception.getMessage());

	}

	@Test
	void check_film_duration_1() {
		Film film = new Film("Интерстеллар", "",
				LocalDate.of(1895, 12, 28),
				0);
		filmManager.create(film);
		assertEquals(1, filmManager.getItems().size());
	}

	@Test
	void check_film_duration_2() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						Film film = new Film("Интерстеллар", "",
								LocalDate.of(1974, 9, 14),
								-1);
						filmManager.create(film);
					}
				});

		assertEquals("Неверное значение продолжительности фильма", exception.getMessage());

	}

	@Test
	void check_film_duration_3() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						Film film = new Film("Интерстеллар", "",
								LocalDate.of(1974, 9, 14),
								-100);
						filmManager.create(film);
					}
				});

		assertEquals("Неверное значение продолжительности фильма", exception.getMessage());

	}

	@Test
	void check_user_email() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						User user = new User("aaa",
								"login",
								 LocalDate.of(1985, 2, 8));
						userManager.create(user);
					}
				});

		assertEquals("Неверный почтовый адрес", exception.getMessage());

	}

	@Test
	void check_user_empty_login_1() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						User user = new User("aaa@bbb",
								"",
								LocalDate.of(1985, 2, 8));
						userManager.create(user);
					}
				});

		assertEquals("Логин не указан", exception.getMessage());

	}

	@Test
	void check_user_empty_login_2() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						User user = new User("aaa@bbb",
								"фф ",
								LocalDate.of(1985, 2, 8));
						userManager.create(user);
					}
				});

		assertEquals("Логин не может содержать пробелы", exception.getMessage());

	}

	@Test
	void check_user_birthdate_1() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						User user = new User("aaa@bbb",
								"login",
								LocalDate.now().plusDays(1));
						userManager.create(user);
					}
				});

		assertEquals("Неверная дата рождения", exception.getMessage());

	}

	void check_user_birthdate_2() {

		final ValidationException exception = assertThrows(

				ValidationException.class,

				new Executable() {
					@Override
					public void execute() {
						User user = new User("aaa@bbb",
								"login",
								LocalDate.now().plusDays(100));
						userManager.create(user);
					}
				});

		assertEquals("Неверная дата рождения", exception.getMessage());

	}

	@Test
	void check_user_birthdate_3() {

		User user = new User("aaa@bbb",
				"Diana",
				LocalDate.of(1961, 7, 1));

		userManager.create(user);

		assertEquals(1, userManager.getItems().size());
	}

}