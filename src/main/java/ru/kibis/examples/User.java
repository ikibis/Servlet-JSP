package ru.kibis.examples;

public class User {
    private String name;
    private String lastName;
    private String sex;
    private String desc;

    public User(String name, String lastName, String sex, String desc) {
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
