package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    private static class Holder {
        private static final Validate INSTANCE = new ValidateStub();
    }

    public static Validate getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        if (this.findByLogin(user.getLogin()) == null && this.findByEmail(user.getContacts().getEmail()) == null) {
            user.setId(this.ids++);
            this.store.put(user.getId(), user);
            result = true;
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
        if (!updatedUser.getContacts().getEmail().equals(user.getContacts().getEmail())
                && this.findByEmail(updatedUser.getContacts().getEmail()) == null) {
            user.getContacts().setEmail(updatedUser.getContacts().getEmail());
            result = true;
        }
        if (!updatedUser.getPassword().equals(user.getPassword())) {
            user.setPassword(updatedUser.getPassword());
            result = true;
        }
        if (!updatedUser.getRole().equals(user.getRole())) {
            user.setRole(updatedUser.getRole());
            result = true;
        }
        return result;
    }

    @Override
    public void delete(int id) {
        if (this.findById(id) != null) {
            this.store.remove(id);
        }
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store.values());
    }

    public User findByLogin(String login) {
        User result = null;
        for (User userSearched : this.store.values()) {
            String userSearchedLogin = userSearched.getLogin();
            if (userSearchedLogin.equals(login)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }

    @Override
    public List<String> findCountries() {
        return null;
    }

    @Override
    public List<String> findCities(String country) {
        return null;
    }

    public User findByEmail(String email) {
        User result = null;
        for (User userSearched : this.store.values()) {
            String userSearchedEmail = userSearched.getContacts().getEmail();
            if (userSearchedEmail.equals(email)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }


    public User findById(int id) {
        User result = null;
        for (User userSearched : this.store.values()) {
            int userSearchedId = userSearched.getId();
            if (userSearchedId == id) {
                result = userSearched;
                break;
            }
        }
        return result;
    }

    @Override
    public User isCredentional(String login, String password) {
        return null;
    }

    public void clean() {
        this.store.clear();
        this.ids = 0;
    }
}
