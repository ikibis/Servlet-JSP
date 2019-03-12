package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;

public interface Validate {
    boolean add(User user);

    List<User> findAll();

    boolean update(User user, User udatedUser);

    void delete(int id);

    User findById(int id);

    User isCredentional(String login, String password);

    void clean();

    User findByLogin(String login);

    List<String> findCountries();

    List<String> findCities(String country);
}
