package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;

public interface Validate {
    public User add(User user);

    public List<User> findAll();

    public User update(User user, User udatedUser);

    public boolean delete(User user);
}
