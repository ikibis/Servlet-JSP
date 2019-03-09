package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;

public interface Validate {
    public boolean add(User user);

    public List<User> findAll();

    public boolean update(User user, User udatedUser);

    public void delete(int id);

    User findById(int id);

    User isCredentional(String login, String password);

    public void clean();

    public User findByLogin(String login);
}
