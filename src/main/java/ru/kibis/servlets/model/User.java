package ru.kibis.servlets.model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final static AtomicInteger IDENTIFIER = new AtomicInteger(1);
    private int id;
    private String login;
    private String password;
    private String role;
    private String createDate;
    private Contacts contacts;

    public void setId(int id) {
        this.id = id;
    }

    public User(String login, String password, String createDate, Role role, Contacts contacts) {
        this.id = IDENTIFIER.getAndIncrement();
        this.login = login;
        this.password = password;
        this.role = String.valueOf(role);
        this.createDate = createDate;
        this.contacts = contacts;
    }

    public User(String login, String password, Role role, Contacts contacts) {
        this.login = login;
        this.password = password;
        this.role = String.valueOf(role);
        this.contacts = contacts;
    }

    public User(String id, String login, String password, String createDate, Role role, Contacts contacts) {
        this.id = Integer.parseInt(id);
        this.login = login;
        this.password = password;
        this.role = String.valueOf(role);
        this.createDate = createDate;
        this.contacts = contacts;
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

    public String getCreateDate() {
        return createDate;
    }

    public Contacts getContacts() {
        return contacts;
    }
}
