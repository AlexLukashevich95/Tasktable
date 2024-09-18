-- Создание таблицы tasks
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    deadline TIMESTAMP,
    status VARCHAR(50) NOT NULL
);

-- Создание таблицы tasks
CREATE TABLE stats (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    date TIMESTAMP NOT NULL
);