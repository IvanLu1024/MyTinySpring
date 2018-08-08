package com.ivan.tinySpring.aop;

public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
