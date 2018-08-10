package com.ivan.tinySpring.aop;

/**
 * 被代理的对象
 */

public class TargetSource {
    //代理对象的类型
    private Class<?> targetClass;
    //代理对象的实例
    private Object target;

    private Class<?>[] interfaces;

    public TargetSource( Object target,Class<?> targetClass,Class<?>... interfaces) {
        this.targetClass = targetClass;
        this.target = target;
        this.interfaces=interfaces;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }
}
