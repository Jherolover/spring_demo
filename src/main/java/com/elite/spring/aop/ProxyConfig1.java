package com.elite.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

@Configuration
public class ProxyConfig1 {

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
    //定义一个增强器：interceptor1，内部是一个前置通知，需要将其包装为Advisor类型的
    @Bean
    public Advisor interceptor1() {
        MethodBeforeAdvice advice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, @Nullable Object
                    target) throws Throwable {
                System.out.println("准备调用：" + method);
            }
        };
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(advice);
        return advisor;
    }
    //定义一个增强器：interceptor2
    @Bean
    public MethodInterceptor interceptor2() {
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                long starTime = System.nanoTime();
                Object result = invocation.proceed();
                long endTime = System.nanoTime();
                System.out.println(invocation.getMethod() + ",耗时(纳秒)：" +
                        (endTime - starTime));
                return result;
            }
        };
        return methodInterceptor;
    }
    //定义一个后置通知
    @Bean
    public AfterReturningAdvice afterReturningAdvice() {
        AfterReturningAdvice afterReturningAdvice = new AfterReturningAdvice() {
            @Override
            public void afterReturning(@Nullable Object returnValue, Method
                    method, Object[] args, @Nullable Object target) throws Throwable {
                System.out.println(method + "，执行完毕!");
            }
        };
        return afterReturningAdvice;
    }
   //注册一个后置通知
//    @Bean
//    public MethodInterceptor methodInterceptor(){
//        MethodInterceptor methodInterceptor = new MethodInterceptor() {
//            @Override
//            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
//                long starttime = System.nanoTime();
//                Object result = methodInvocation.proceed();
//                long endtime = System.nanoTime();
//                System.out.println("方法调用时间："+methodInvocation.getMethod()+"耗时纳秒："+(endtime-starttime));
//                return result;
//            }
//        };
//        return methodInterceptor;
//    };
    //注册proxyfactorybean
    @Bean
    public ProxyFactoryBean service1Proxy() {
        //1.创建ProxyFactoryBean
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //2.设置目标对象的bean名称
        proxyFactoryBean.setTargetName("service1");
        //3.设置拦截器的bean名称列表，此处2个（advice1和advice2)
        proxyFactoryBean.setInterceptorNames("interceptor*","afterReturningAdvice");
        return proxyFactoryBean;
    }

}
