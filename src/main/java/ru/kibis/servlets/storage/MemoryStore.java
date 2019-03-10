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
            String userSearchedEmail = userSearched.getContacts().getEmail();
            if (userSearchedLogin != null && userSearchedEmail != null) {
                if (userSearchedLogin.equals(user.getLogin()) || userSearchedEmail.equals(user.getContacts().getEmail())) {
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
        if (!updatedUser.getContacts().getName().equals(user.getContacts().getName())) {
            user.getContacts().setName(updatedUser.getContacts().getName());
            result = true;
        }
        if (!updatedUser.getLogin().equals(user.getLogin()) && this.findByLogin(updatedUser.getLogin()) == null) {
            user.setLogin(updatedUser.getLogin());
            result = true;
        }
        if (!updatedUser.getContacts().getEmail().equals(user.getContacts().getEmail()) && this.findByEmail(updatedUser.getContacts().getEmail()) == null) {
            user.getContacts().setEmail(updatedUser.getContacts().getEmail());
            result = true;
        }
        if (!updatedUser.getPassword().equals(user.getPassword())) {
            user.setPassword(updatedUser.getPassword());
            result = true;
        }
        if (!updatedUser.getContacts().getCountry().equals(user.getContacts().getCountry())) {
            user.getContacts().setCountry(updatedUser.getContacts().getCountry());
            result = true;
        }
        if (!updatedUser.getContacts().getCity().equals(user.getContacts().getCity())) {
            user.getContacts().setCity(updatedUser.getContacts().getCity());
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

    @Override
    public List<String> getCountries() {
        return null;
    }

    @Override
    public List<String> getCities(String country) {
        return null;
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
            String userSearchedEmail = userSearched.getContacts().getEmail();
            if (userSearchedEmail.equals(email)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }
}
