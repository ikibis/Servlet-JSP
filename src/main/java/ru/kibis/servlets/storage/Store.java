package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;

public interface Store {
    boolean add(User user);

    boolean update(User user, User updatedUser);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);

    List<String> getCountries();

    List<String> getCities(String country);
}
