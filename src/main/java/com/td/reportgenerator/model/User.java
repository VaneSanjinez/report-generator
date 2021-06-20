package com.td.reportgenerator.model;


public class User {
    public int id;
    public String name;
    public String username;

    public User(int id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
