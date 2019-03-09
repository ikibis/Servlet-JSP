package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ValidateService implements Validate {
    //private final Store memory = MemoryStore.getInstance();
    private final Store memory = DbStore.getInstance();

    private ValidateService() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        String date = sdf.format(new Date());
        User root = new User("root", "root", "root", "root@root", date, Role.ROOT);
        add(root);
    }

    private static class Holder {
        private static final Validate INSTANCE = new ValidateService();
    }

    public static Validate getInstance() {
        return Holder.INSTANCE;
    }

    public boolean add(User user) {
        boolean result = false;
        if (user.getName() != null && user.getLogin() != null
                && user.getPassword() != null && user.getEmail() != null) {
            result = memory.add(user);
        }
        return result;
    }

    public boolean update(User user, User udatedUser) {
        boolean result = false;
        if (udatedUser.getName() != null && udatedUser.getLogin() != null
                && udatedUser.getPassword() != null && udatedUser.getEmail() != null) {
            result = memory.update(user, udatedUser);
        }
        return result;
    }

    public void delete(int id) {
        User user = this.findById(id);
        memory.delete(user);
    }

    public List<User> findAll() {
        return memory.findAll();
    }

    public User findById(int id) {
        return memory.findById(id);
    }

    public User isCredentional(String login, String password) {
        User result = null;
        for (User user : this.findAll()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public void clean() {

    }

    @Override
    public User findByLogin(String login) {
        return null;
    }
}
