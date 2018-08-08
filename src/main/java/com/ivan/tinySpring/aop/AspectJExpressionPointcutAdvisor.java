package com.ivan.tinySpring.aop;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements  PointcutAdvisor {
    private AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();

    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
