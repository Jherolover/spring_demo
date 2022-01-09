package com.elite.spring.beans;

public class Userfactory {
    public User buildUser1() {
        System.out.println("----------------------1");
        User userModel = new User();
        userModel.setUsername("bean实例方法创建的对象!");
        return userModel;
    }
    public User buildUser2(String name, String sexcode) {
        System.out.println("----------------------2");
        User userModel = new User();
        userModel.setUsername(name);
        userModel.setSexcode(sexcode);
        return userModel;
    }
}
