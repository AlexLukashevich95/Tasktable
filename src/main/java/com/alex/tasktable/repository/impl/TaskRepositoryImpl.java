package com.alex.tasktable.repository.impl;

import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.exceptions.BadRequestException;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.repository.TaskRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Task> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.setDefaultReadOnly(true);
            return session.createQuery("FROM Task", Task.class).list();
        }
    }

    @Override
    public Task findById(Long id) throws ApplicationException {
        try (Session session = sessionFactory.openSession()) {
            session.setDefaultReadOnly(true);
            Task task = session.get(Task.class, id);
            if (task == null) {
                throw new BadRequestException("Task not found with ID: " + id);
            }
            return task;
        }
    }


    @Override
    public Task save(Task task) throws ApplicationException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
            return task;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Error saving task", e);
        }
    }

    @Override
    public Task update(Task task) throws ApplicationException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
            return task;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Error saving task", e);
        }
    }

    @Override
    public void deleteById(Long id) throws ApplicationException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.delete(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Error deleting task", e);
        }
    }
}


