package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public boolean add(User user) {
        boolean result = false;
        if (this.findByLogin(user.getLogin()) == null && this.findByEmail(user.getEmail()) == null) {
            user.setId(this.ids++);
            this.store.put(user.getId(), user);
            result = true;
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

    public User findByEmail(String email) {
        User result = null;
        for (User userSearched : this.store.values()) {
            String userSearchedEmail = userSearched.getEmail();
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
}
