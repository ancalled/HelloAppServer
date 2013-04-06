
INSERT INTO cities(name, title) VALUES ('almaty', 'Алматы');
INSERT INTO cities(name, title) VALUES ('astana', 'Астана');

INSERT INTO categories(title, name) VALUES ('Бары', 'bars-pubs');
INSERT INTO categories(title, name) VALUES ('Кафе', 'cafes');
INSERT INTO categories(title, name) VALUES ('Кинотеатры', 'cinemas');
INSERT INTO categories(title, name) VALUES ('Кофейни', 'coffee-houses');

INSERT INTO places(name, title, category, city) VALUES ('chukotka', 'Чукотка', 'bars-pubs', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('totbar', 'Тот самый бар', 'bars-pubs', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('nebar', 'Nebar', 'bars-pubs', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('mojo', 'Mojo', 'bars-pubs', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('fantomas', 'Синий Fun To Mass', 'bars-pubs', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('zhest', 'Жесть', 'bars-pubs', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('cafeteria', 'Cafeteria', 'coffee-houses', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('shokoladnica', 'Шоколадница', 'coffee-houses', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('cofedelia', 'Coffeedelia', 'coffee-houses', 'almaty');
INSERT INTO places(name, title, category, city) VALUES ('madlen', 'Мадлен', 'coffee-houses', 'almaty');
# INSERT INTO places(name, title, category, city) VALUES ('', '', 'coffee-houses', 'almaty');


INSERT INTO events(id, title, place, eventDate, description) VALUES (1, 'Концерт Дельфина', 'zhest', '2013-03-22 21:00:00', ' На концерте мы услышим известные боевики, а также песни из нового альбома «Существо». Начало концерта в 23:00. Цена билета 5000 тг. и 8000 тг. (балкон)');


# ---------------------------------------------------------------------------

INSERT INTO users(id, name, pass) VALUES (1, 'user1', '12345');
INSERT INTO users(id, name, pass) VALUES (2, 'user2', '12345');
INSERT INTO users(id, name, pass) VALUES (3, 'user3', '12345');

# INSERT INTO campaign_stats(id, campaign_id, user_id, whenApllied) VALUES (1, 3, 1, '2013-04-05 13:15');
# INSERT INTO campaign_stats(id, campaign_id, user_id, whenApllied) VALUES (2, 3, 2, '2013-04-06 20:56');





