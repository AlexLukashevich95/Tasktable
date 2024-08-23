package com.alex.tasktable.repository.impl;

import com.alex.tasktable.model.Task;
import com.alex.tasktable.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll() {
        String query = "SELECT * FROM tasks";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Task.class));
    }

    @Override
    public Task findById(Long id) {
        String query = "SELECT * FROM tasks WHERE id=?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Task.class));
    }

    @Override
    public Task save(Task task) {
        String query = "INSERT INTO tasks (name, description, deadline, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, task.getName(), task.getDescription(), task.getDeadline(), task.getStatus());
        return task;
    }

    @Override
    public Task update(Task task) {
        String query="update tasks SET name = ?, description = ?, deadline = ?, status = ? where id = ?";
        jdbcTemplate.update(query, task.getName(), task.getDescription(), task.getDeadline(), task.getStatus(), task.getId());
        return task;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM tasks WHERE id=?";
        jdbcTemplate.update(query, id);
    }
}
