package com.github.andreytemn.service;

import com.github.andreytemn.model.User;

import java.util.List;

/**
 * Created by Андрей on 20.05.2016.
 */
public interface UserService {
    void add(User user);

    void update(User user);

    void delete(int id);

    User get(int id);

    List<User> list();
}
