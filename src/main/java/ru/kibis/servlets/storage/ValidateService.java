package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ValidateService {
    private static ValidateService service;
    //private final Store memory = MemoryStore.getInstance();
    private final Store memory = DbStore.getInstance();

    public ValidateService() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        String date = sdf.format(new Date());
        User root = new User("root", "root", "root", "root@root", date, Role.ROOT);
        add(root);
    }

    public static ValidateService getInstance() {
        if (service == null) {
            service = new ValidateService();
        }
        return service;
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
        User user = this.getUserById(id);
        memory.delete(user);
    }

    public List<User> findAll() {
        return memory.findAll();
    }

    public boolean findById(int id) {
        boolean result = false;
        if (memory.findById(id) != null) {
            result = true;
        }
        return result;
    }

    public User getUserById(int id) {
        User result = null;
        if (memory.findById(id) != null) {
            result = memory.findById(id);
        }
        return result;
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
}
