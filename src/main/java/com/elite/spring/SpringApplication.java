package com.elite.spring;

import com.elite.spring.aop.ProxyConfig;
import com.elite.spring.aop.ProxyConfig1;
import com.elite.spring.aop.Service1;
import com.elite.spring.beans.Hello;
import com.elite.spring.beans.User;
import com.elite.spring.config.ConfigBean;
import com.elite.spring.proxy.IService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 主要类
 */
public class SpringApplication {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
      //配置文件的路径;
//        String beanxml = "classpath:config/beans.xml";
//        //创建容器，加载配置文件
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanxml);
        //从容器中获取bean
        //Hello hello= context.getBean("hello",Hello.class);
        //User user = context.getBean("user2",User.class);
        //System.out.println(user.toString());
//        System.out.println(context.getBean("userfacory"));
//        System.out.println(context.getBean("userfacory"));
//        System.out.println(context.getBean("userfacory"));
        //使用类
        //hello.sayHello();

        //测试代理创建方式1==========================
        // 1. 获取接口对应的代理类
//        Class<IService> proxyClass = (Class<IService>)
//                Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
//        // 2. 创建代理类的处理器
//        InvocationHandler invocationHandler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws
//                    Throwable {
//                System.out.println("我是InvocationHandler，被调用的方法是：" +
//                        method.getName());
//                return null;
//            }
//        };
//        // 3. 创建代理实例
//        IService proxyService =
//                proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler
//                );
//        // 4. 调用代理的方法
//        proxyService.m1();
//        proxyService.m2();
//        proxyService.m3();

        //测试代理创建方式1==========================
        //测试代理创建方式2==========================
        // 1. 创建代理类的处理器
//        InvocationHandler invocationHandler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws
//                    Throwable {
//                System.out.println("我是InvocationHandler创建方式2，被调用的方法是：" +
//                        method.getName());
//                return null;
//            }
//        };
//        // 2. 创建代理实例
//        IService proxyService = (IService)
//                Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]
//                        {IService.class}, invocationHandler);
//        // 3. 调用代理的方法
//        proxyService.m1();
//        proxyService.m2();
//        proxyService.m3();
        //测试代理创建方式2==========================
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);
        //User user = (User) context.getBeanDefinition("user");
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(ProxyConfig1.class);
        //获取代理对象，代理对象bean的名称为注册ProxyFactoryBean的名称，即：service1Proxy
        Service1 bean = context.getBean("service1Proxy", Service1.class);
        System.out.println("----------------------");
        //调用代理的方法
        bean.m1();
        System.out.println("----------------------");
        //调用代理的方法
        bean.m2();


    }
}
