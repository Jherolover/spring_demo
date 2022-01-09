package com.elite.spring.beans;

public class User {
    private Integer id;
    private String username;
    private String sexcode;

    public User() {
    }

    public User(Integer id, String username, String sexcode) {
        this.id = id;
        this.username = username;
        this.sexcode = sexcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSexcode() {
        return sexcode;
    }

    public void setSexcode(String sexcode) {
        this.sexcode = sexcode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sexcode='" + sexcode + '\'' +
                '}';
    }
}
