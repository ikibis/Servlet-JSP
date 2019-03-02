package ru.kibis.servlets.model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final static AtomicInteger IDENTIFIER = new AtomicInteger(0);
    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String createDate;

    public User(String name, String login, String password, String email, String createDate) {
        this.id = IDENTIFIER.getAndIncrement();
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String name, String login, String password, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
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
