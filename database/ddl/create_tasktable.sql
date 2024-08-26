-- Создание таблицы tasks
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,           -- Уникальный идентификатор задачи
    name VARCHAR(255) NOT NULL,      -- Название задачи
    description TEXT,                -- Описание задачи
    deadline DATE,                   -- Крайний срок выполнения задачи
    status VARCHAR(50) NOT NULL     -- Статус задачи (например, 'Pending', 'In Progress', 'Completed')
);