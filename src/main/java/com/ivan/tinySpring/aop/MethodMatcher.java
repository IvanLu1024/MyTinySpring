package com.ivan.tinySpring.aop;

import java.lang.reflect.Method;

/**
 * 用于判断是否对某个方法进行拦截（用于在代理对象中对不同的方法进行不同的操作）
 */
public interface MethodMatcher {

    boolean matches(Method method,Class targetClass);
}
