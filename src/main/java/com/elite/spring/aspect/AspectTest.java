package com.elite.spring.aspect;


import com.elite.spring.aspect.service.Service;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class AspectTest {

    @Test
    public void test1(){
        try {
            //代理的目标对象
            Service target = new Service();
            //创建aspectjProxyFactory
            AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
            //设置代理目标对象
            proxyFactory.setTarget(target);
            //设置标注了@Aspect 注解的类
            proxyFactory.addAspect(Aspect1.class);
            //生成代理对象
            Service proxy = proxyFactory.getProxy();
            //使用代理对象
            proxy.m1();
            proxy.m2();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
