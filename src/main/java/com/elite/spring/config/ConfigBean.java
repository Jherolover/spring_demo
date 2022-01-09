package com.elite.spring.config;

import com.elite.spring.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过注解创建类
 */
@Configuration
public class ConfigBean {

    @Bean(name = "user",value = "",autowireCandidate = false)
    public User createUser(){
        return new User();
    }

}
