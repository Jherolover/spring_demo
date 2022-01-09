package com.elite.spring.beans;

import org.springframework.beans.factory.FactoryBean;

public class UserfactoryBean implements FactoryBean<User> {


    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setUsername("通过factorybean创建的user对象");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
