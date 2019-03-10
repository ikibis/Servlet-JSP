package ru.kibis.examples;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class User {
    private String name;
    private String lastName;
    private String sex;
    private String description;

    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("lastName") String lastName,
                @JsonProperty("sex") String sex, @JsonProperty("description") String description) {
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.description = description;
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
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }
}
