package com.elite.spring.aop;

import com.elite.spring.beans.User;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class aoptest {

    @Test
    public void test1(){
        //定义目标对象
        Userservice target = new Userservice();
        //创建pointcut 用来拦截userservice 的work方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                //判断是否是userservice类型
                return clazz -> Userservice.class.isAssignableFrom(clazz);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> aClass) {
                        //判断是否是work方法
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        return false;
                    }
                };
            }
        };
        //创建通知，此处需要在方法执行之前执行，需要methodbeforeAdvice类型的通知
        MethodBeforeAdvice advice = (method,args,target1) ->
                System.out.println("参数:"+args[0]);
        //创建advisor,将pointcut和advice 组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,advice);
        //通过spring 提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用advisor方法，为目标方法增强的功能，添加advisor，可以为目标添加多个
        proxyFactory.addAdvisor(advisor);
        //代理工厂生成代理对象
        Userservice userserviceProxy = (Userservice) proxyFactory.getProxy();
        //执行方法
        userserviceProxy.work("elite1");
    }

    /**
     * 测试调用方法耗时统计
     */
    @Test
    public void test2(){
        //定义目标对象
        Userservice target = new Userservice();
        //创建pointcut 用来拦截userservice 的work方法
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                //判断是否是userservice类型
                return clazz -> Userservice.class.isAssignableFrom(clazz);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> aClass) {
                        //判断是否是work方法
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        return false;
                    }
                };
            }
        };
        //创建拦截方法执行对象
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("准备调用:" + invocation.getMethod());
                long starTime = System.nanoTime();
                Object result = invocation.proceed();
                long endTime = System.nanoTime();
                System.out.println(invocation.getMethod() + "，调用结束！");
                System.out.println("耗时(纳秒):" + (endTime - starTime));
                return result;
            }
        };
        //创建通知，此处需要在方法执行之前执行，需要methodbeforeAdvice类型的通知
//        MethodBeforeAdvice advice = (method,args,target1) ->
//                System.out.println("参数:"+args[0]);
        //创建advisor,将pointcut和advice 组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,methodInterceptor);
        //通过spring 提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用advisor方法，为目标方法增强的功能，添加advisor，可以为目标添加多个
        proxyFactory.addAdvisor(advisor);
        //代理工厂生成代理对象
        Userservice userserviceProxy = (Userservice) proxyFactory.getProxy();
        //执行方法
        userserviceProxy.work("elite1");
    }

    /**
     * 模拟基金操作
     */
    @Test
    public void test3() {
        //代理工厂
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        //添加一个方法前置通知，判断用户名不是“路人”的时候，抛出非法访问异常
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, @Nullable Object
                    target) throws Throwable {
                String userName = (String) args[0];
                //如果不是路人的时候，抛出非法访问异常
                        if (!"路人".equals(userName)) {
                            throw new RuntimeException(String.format("[%s]非法访问!",
                                    userName));
                        }
                    }
        });
        //通过代理工厂创建代理
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        //调用代理的方法
        proxy.recharge("路人", 100);
        proxy.recharge("张学友", 100);
    }
}
