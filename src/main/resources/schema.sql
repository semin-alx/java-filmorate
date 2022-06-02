CREATE TABLE IF NOT EXISTS genre (
	genre_id INT,
	name VARCHAR(100),
	PRIMARY KEY (genre_id)
);

CREATE TABLE IF NOT EXISTS rating_mpa (
	mpa_id INT,
	name VARCHAR(10),
	descr VARCHAR(100),
	PRIMARY KEY (mpa_id)
);

CREATE TABLE IF NOT EXISTS film (
	film_id INT AUTO_INCREMENT,
	name VARCHAR(100),
	descr VARCHAR(200),
	release_date DATE,
	duration INT,
	mpa_id INT,
	PRIMARY KEY (film_id),
	FOREIGN KEY (mpa_id) REFERENCES rating_mpa(mpa_id)
);

CREATE TABLE IF NOT EXISTS film_genre (
	film_id INT,
	genre_id INT,
	PRIMARY KEY (film_id, genre_id),
	FOREIGN KEY (film_id) REFERENCES film(film_id),
	FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);

CREATE TABLE IF NOT EXISTS user (
	user_id int AUTO_INCREMENT,
	login VARCHAR(50),
	name VARCHAR(100),
	email VARCHAR(50),
	birthday DATE,
	PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS likes (
	user_id INT,
	film_id INT,
	PRIMARY KEY (user_id, film_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id),
	FOREIGN KEY (film_id) REFERENCES film(film_id)
);

CREATE TABLE IF NOT EXISTS friend (
	user_id1 INT,
	user_id2 INT,
	PRIMARY KEY (user_id1, user_id2),
	FOREIGN KEY (user_id1) REFERENCES user(user_id),
	FOREIGN KEY (user_id2) REFERENCES user(user_id)
);