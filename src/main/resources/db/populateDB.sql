DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE meal_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, calories, user_id, date_time)
VALUES ('Завтрак', 1000, 100000, TIMESTAMP '2019-06-01 09:00:00'),
       ('Обед', 250, 100000, TIMESTAMP '2019-06-01 13:00:00'),
       ('Ужин', 500, 100000, TIMESTAMP '2019-06-01 18:00:00'),
       ('Завтрак', 1200, 100001, TIMESTAMP '2019-06-01 10:00:00'),
       ('Обед', 350, 100001, TIMESTAMP '2019-06-01 14:00:00'),
       ('Ужин', 700, 100001, TIMESTAMP '2019-06-01 19:00:00');
