package com.alex.tasktable.repository.impl;

import com.alex.tasktable.exceptions.BadRequestException;
import com.alex.tasktable.exceptions.ResourceNotFoundException;
import com.alex.tasktable.exceptions.TaskException;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.repository.TaskRepository;
import com.alex.tasktable.utils.Utility;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    @Autowired
    private BasicDataSource basicDataSource;

    private static final String SELECT_TASK_SQL =
            "SELECT * " +
                    "FROM tasks " +
                    "WHERE id=? ";

    private static final String SELECT_ALL_TASKS_SQL =
            "SELECT id, name, description, deadline, status " +
                    "FROM tasks ";

    private static final String INSERT_TASK_SQL =
            "INSERT INTO tasks (name, description, deadline, status)" +
                    "VALUES (?, ?, ?, ?)";

    private static final String UPDATE_TASK_SQL =
            "UPDATE tasks " +
                    "SET name = ?, " +
                    "    description = ?, " +
                    "    deadline = ?, " +
                    "    status = ? " +
                    "WHERE id = ? ";

    private static final String DELETE_TASK_SQL =
            "DELETE FROM tasks " +
                    "WHERE id=?";

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = Utility.getConnection(basicDataSource);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS_SQL);
             ResultSet resultSet = Utility.getResultSet(preparedStatement)) {
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getTimestamp("deadline").toLocalDateTime());
                task.setStatus(resultSet.getString("status"));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            throw new BadRequestException("f");
        }
    }

    @Override
    public Task findById(Long id) {
        try (ResultSet resultSet = Utility.getResultSet(
                Utility.getPrepareStatement(basicDataSource, SELECT_TASK_SQL, List.of(id)))) {
            Task task = new Task();
            if (resultSet.next()) {
                task.setId(id);
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getTimestamp("deadline").toLocalDateTime());
                task.setStatus(resultSet.getString("status"));
            }
            return task;
        } catch (SQLException e) {
            throw new BadRequestException("f");
        }
    }


    @Override
    public Task save(Task task) {
        try (PreparedStatement preparedStatement = Utility.getPrepareStatement(basicDataSource, INSERT_TASK_SQL,
                List.of(
                        task.getName(),
                        task.getDescription(),
                        task.getDeadline(),
                        task.getStatus()
                )
        )
        ) {
            if (Utility.executeUpdate(preparedStatement) == 0)
                throw new BadRequestException("f");
            return task;
        } catch (SQLException e) {
            throw new BadRequestException("f");
        }
    }

    @Override
    public Task update(Task task) {
        try (PreparedStatement preparedStatement = Utility.getPrepareStatement(basicDataSource, UPDATE_TASK_SQL,
                List.of(
                        task.getName(),
                        task.getDescription(),
                        task.getDeadline(),
                        task.getStatus(),
                        task.getId()
                )
        )
        ) {
            if (Utility.executeUpdate(preparedStatement) == 0) {
                throw new BadRequestException("f");
            }
            return task;
        } catch (SQLException e) {
            throw new BadRequestException("f");
        }
    }

    @Override
    public void deleteById(Long id)  {
        try (PreparedStatement preparedStatement =
                     Utility.getPrepareStatement(basicDataSource, DELETE_TASK_SQL, List.of(id))) {

            if (Utility.executeUpdate(preparedStatement) == 0) {
                throw new BadRequestException("f");
            }
        } catch (SQLException e) {
            throw new BadRequestException("f");
        }
    }
}


