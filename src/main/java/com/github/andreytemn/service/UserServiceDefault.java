package com.github.andreytemn.service;

import com.github.andreytemn.dao.UserDAO;
import com.github.andreytemn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Андрей on 20.05.2016.
 */
@Service("userService")
@Transactional(readOnly = false, value = "hibernateTransactionManager")
public class UserServiceDefault implements UserService {
    @Autowired
    @Qualifier("getUserDAO")
    UserDAO userDAO;

    public void add(User user) {
        userDAO.add(user);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public void delete(int id) {
        userDAO.delete(id);
    }

    public User get(int id) {
        return userDAO.get(id);
    }

    public List<User> list() {
        return userDAO.list();
    }
}
