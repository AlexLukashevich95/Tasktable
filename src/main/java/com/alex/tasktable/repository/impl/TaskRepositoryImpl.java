package com.alex.tasktable.repository.impl;

import com.alex.tasktable.model.Task;
import com.alex.tasktable.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Task> findAll() {
        String query = "SELECT id,name,description,deadline,status FROM tasks";
        List<Task> taskList = new ArrayList<Task>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setDeadline(rs.getDate("deadline"));
                task.setStatus(rs.getString("status"));
                taskList.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public Task findById(Long id) {
        String query = "SELECT * FROM tasks WHERE id=?";
        Task task = null;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    task = new Task();
                    task.setId(id);
                    task.setName(rs.getString("name"));
                    task.setDescription(rs.getString("description"));
                    task.setDeadline(rs.getDate("deadline"));
                    task.setStatus(rs.getString("status"));
                    System.out.println("Task Found::" + task);
                } else {
                    System.out.println("No Employee found with id=" + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Task save(Task task) {
        String query = "INSERT INTO tasks (name, description, deadline, status) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);

            con.setAutoCommit(false);

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setDate(3, new java.sql.Date(task.getDeadline().getTime()));
            ps.setString(4, task.getStatus());

            int out = ps.executeUpdate();
            if (out != 0) {
                con.commit();
                System.out.println("task saved with id=" + task.getId());
            } else {
                System.out.println("task save failed with id=" + task.getId());
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Откатываем транзакцию при ошибке
                    System.out.println("Transaction rolled back due to error: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null) con.setAutoCommit(true); // Восстанавливаем автокоммит
                if (con != null) con.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return task;
    }

    @Override
    public Task update(Task task) {
        String query = "update tasks SET name = ?, description = ?, deadline = ?, status = ? where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);

            con.setAutoCommit(false);

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setDate(3, new java.sql.Date(task.getDeadline().getTime()));
            ps.setString(4, task.getStatus());
            ps.setLong(5, task.getId());

            int out = ps.executeUpdate();
            if (out != 0) {
                con.commit();
                System.out.println("Task updated with id=" + task.getId());
            } else {
                System.out.println("No Task found with id=" + task.getId());
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Откатываем транзакцию при ошибке
                    System.out.println("Transaction rolled back due to error: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null) con.setAutoCommit(true); // Восстанавливаем автокоммит
                if (con != null) con.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return task;
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM tasks WHERE id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);

            con.setAutoCommit(false);

            ps.setLong(1, id);

            int out = ps.executeUpdate();
            if (out != 0) {
                con.commit();
                System.out.println("Task deleted with id=" + id);
            } else {
                System.out.println("No Task found with id=" + id);
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Откатываем транзакцию при ошибке
                    System.out.println("Transaction rolled back due to error: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null) con.setAutoCommit(true); // Восстанавливаем автокоммит
                if (con != null) con.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


