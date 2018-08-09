package com.ivan.tinySpring.aop;

/**
 * 用于判断是否对某个对象进行拦截（用于筛选要代理的目标对象）
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
