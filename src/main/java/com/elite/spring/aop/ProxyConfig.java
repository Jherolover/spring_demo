package com.elite.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class ProxyConfig {

    //注册类
    @Bean
    public Service1 service1(){
        return new Service1();
    }

    //注册一个前置通知
    @Bean
    public MethodBeforeAdvice methodBeforeAdvice(){
        MethodBeforeAdvice advice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("调用方法"+method.getName());
            }
        };
        return advice;
    };
   //注册一个后置通知
    @Bean
    public MethodInterceptor methodInterceptor(){
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                long starttime = System.nanoTime();
                Object result = methodInvocation.proceed();
                long endtime = System.nanoTime();
                System.out.println("方法调用时间："+methodInvocation.getMethod()+"耗时纳秒："+(endtime-starttime));
                return result;
            }
        };
        return methodInterceptor;
    };
    //注册proxyfactorybean
    @Bean
    public ProxyFactoryBean service1Proxy() {
        //1.创建ProxyFactoryBean
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //2.设置目标对象的bean名称
        proxyFactoryBean.setTargetName("service1");
        //3.设置拦截器的bean名称列表，此处2个（advice1和advice2)
        proxyFactoryBean.setInterceptorNames("methodBeforeAdvice",
                "methodInterceptor");
        return proxyFactoryBean;
    }

}
