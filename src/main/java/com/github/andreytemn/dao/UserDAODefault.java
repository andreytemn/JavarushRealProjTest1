package com.github.andreytemn.dao;

import com.github.andreytemn.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Андрей on 20.05.2016.
 */
@Repository
public class UserDAODefault implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void add(User user) {
        System.out.println(user);
        getCurrentSession().save(user);
    }

    public void update(User user) {
        getCurrentSession().merge(user);
    }

    public void delete(int id) {
        User deletingUser = get(id);
        if (deletingUser != null) {
            getCurrentSession().delete(deletingUser);
        }
    }

    public User get(int id) {
        return (User) getCurrentSession().get(User.class, id);
    }

    public List<User> list() {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        return criteria.list();
    }
}
