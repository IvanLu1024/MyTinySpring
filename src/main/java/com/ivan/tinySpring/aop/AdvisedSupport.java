package com.ivan.tinySpring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理中相关的元数据
 *
 */
public class AdvisedSupport {
    //被代理的对象
    private TargetSource targetSource;
    //方法拦截器
    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
