package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Nicken on 09.02.2017.
 */
public class UserServiceImpl implements UserService {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public User get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) throws NotFoundException {

    }
}
