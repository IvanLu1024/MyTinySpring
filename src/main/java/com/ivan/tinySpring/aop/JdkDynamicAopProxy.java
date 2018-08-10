package com.ivan.tinySpring.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于JDK的AOP动态代理
 */
public class JdkDynamicAopProxy implements AopProxy,InvocationHandler {
    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                advisedSupport.getTargetSource().getTargetClass(),this);
    }

    @Override
    public Object invoke(final Object proxy,final Method method,final Object[] args) throws Throwable {
        MethodInterceptor interceptor=advisedSupport.getMethodInterceptor();
        /*if (advisedSupport.getMethodMatcher()!=null
                &&advisedSupport.getMethodMatcher().matches(method,
                    advisedSupport.getTargetSource().getTarget().getClass()))*/
       /* if (advisedSupport.getMethodMatcher().matches(method,
                advisedSupport.getTargetSource().getTarget().getClass())){*/
            return //method.invoke(advisedSupport.getTargetSource().getTarget(),args);
                    interceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),
                    method, args));
       /* } else {
            return method.invoke(advisedSupport.getTargetSource().getTarget(),args);

        }*/



    }
}
