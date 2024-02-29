package com.alex.tasktable.repository.implementation;

import com.alex.tasktable.bean.Task;
import com.alex.tasktable.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll() {
        String query = "SELECT * FROM tasktable";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Task.class));
    }

    @Override
    public Task findById(Long id) {
        String query = "SELECT * FROM tasktable WHERE id=?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Task.class));
    }

    @Override
    public Task save(Task task) {
        String query = "INSERT INTO tasktable (task_name, task_description, task_deadline, task_status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, task.getTask_name(), task.getTask_description(), task.getTask_deadline(), task.getTask_status());
        return task;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM tasktable WHERE id=?";
        jdbcTemplate.update(query, id);
    }
}
