package com.elite.spring.beans;

public class Person {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user=" + user +
                '}';
    }
}
