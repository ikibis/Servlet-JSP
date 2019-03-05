package ru.kibis.servlets.model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final static AtomicInteger IDENTIFIER = new AtomicInteger(1);
    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String role;
    private String createDate;

    public void setId(int id) {
        this.id = id;
    }

    public User(String name, String login, String password, String email, String createDate, Role role) {
        this.id = IDENTIFIER.getAndIncrement();
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = String.valueOf(role);
        this.createDate = createDate;
    }

    public User(String name, String login, String password, String email, Role role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = String.valueOf(role);
    }

    public User(String id, String name, String login, String password, String email, String createDate, Role role) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = String.valueOf(role);
        this.createDate = createDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }
}
