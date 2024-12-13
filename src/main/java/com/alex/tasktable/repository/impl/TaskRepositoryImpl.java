package com.alex.tasktable.repository.impl;

import com.alex.tasktable.exceptions.ApplicationException;
import com.alex.tasktable.model.Task;
import com.alex.tasktable.repository.TaskRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Task> findAll() throws ApplicationException {
        Session session = sessionFactory.getCurrentSession();
        session.setDefaultReadOnly(true);
        return session.createQuery("FROM Task", Task.class).list();

    }

    @Override
    @Transactional
    public Task findById(Long id) throws ApplicationException {
        Session session = sessionFactory.getCurrentSession();
        session.setDefaultReadOnly(true);
        return session.get(Task.class, id);
    }


    @Override
    @Transactional
    public Task save(Task task) throws ApplicationException {
        Session session = sessionFactory.getCurrentSession();
        session.save(task);
        return task;
    }

    @Override
    @Transactional
    public Task update(Task task) throws ApplicationException {
        Session session = sessionFactory.getCurrentSession();
        session.update(task);
        return task;
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws ApplicationException {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        session.delete(task);
    }
}