package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStore implements Store {
    private static Store store;
    private List<User> users = new CopyOnWriteArrayList<>();

    public static Store getInstance() {
        if (store == null) {
            store = new MemoryStore();
        }
        return store;
    }

    @Override
    public boolean add(User user) {
        boolean result = true;
        for (User userSearched : users) {
            String userSearchedLogin = userSearched.getLogin();
            String userSearchedEmail = userSearched.getEmail();
            if (userSearchedLogin != null && userSearchedEmail != null) {
                if (userSearchedLogin.equals(user.getLogin()) || userSearchedEmail.equals(user.getEmail())) {
                    result = false;
                }
            }
        }
        if (result && user != null) {
            users.add(user);
        }
        return result;
    }

    @Override
    public boolean update(User user, User updatedUser) {
        boolean result = false;
        if (!updatedUser.getName().equals(user.getName())) {
            user.setName(updatedUser.getName());
            result = true;
        }
        if (!updatedUser.getLogin().equals(user.getLogin()) && this.findByLogin(updatedUser.getLogin()) == null) {
            user.setLogin(updatedUser.getLogin());
            result = true;
        }
        if (!updatedUser.getEmail().equals(user.getEmail()) && this.findByEmail(updatedUser.getEmail()) == null) {
            user.setEmail(updatedUser.getEmail());
            result = true;
        }
        if (!updatedUser.getPassword().equals(user.getPassword())) {
            user.setPassword(updatedUser.getPassword());
            result = true;
        }
        if (!updatedUser.getCountry().equals(user.getCountry())) {
            user.setCountry(updatedUser.getCountry());
            result = true;
        }
        if (!updatedUser.getCity().equals(user.getCity())) {
            user.setCity(updatedUser.getCity());
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return users.remove(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (User userSearched : users) {
            int userSearchedId = userSearched.getId();
            if (userSearchedId == id) {
                result = userSearched;
                break;
            }
        }
        return result;
    }

    public User findByLogin(String login) {
        User result = null;
        for (User userSearched : users) {
            String userSearchedLogin = userSearched.getLogin();
            if (userSearchedLogin.equals(login)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }

    public User findByEmail(String email) {
        User result = null;
        for (User userSearched : users) {
            String userSearchedEmail = userSearched.getEmail();
            if (userSearchedEmail.equals(email)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }
}
