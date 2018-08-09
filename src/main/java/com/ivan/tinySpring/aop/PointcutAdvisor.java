package com.ivan.tinySpring.aop;

/**
 * 切点通知器
 * 用于提供 对哪个对象的哪个方法进行什么样的拦截的具体内容
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
