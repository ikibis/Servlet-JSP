package ru.kibis.servlets.model;

/**
 * Класс для контактов пользователя
 */
public class Contacts {
    private String name;
    private String email;
    private String country;
    private String city;

    /**
     * Конструктор для объекта контакты пользователя
     *
     * @param name    имя пользователя
     * @param email   адрес электронной почты
     * @param country страна пользователя
     * @param city    город пользователя
     */
    public Contacts(String name, String email, String country, String city) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
