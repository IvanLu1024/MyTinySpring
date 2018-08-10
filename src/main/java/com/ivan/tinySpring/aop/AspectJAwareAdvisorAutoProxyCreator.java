package com.ivan.tinySpring.aop;

import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.beans.beanDefinition.BeanPostProcessor;
import com.ivan.tinySpring.beans.factory.AbstractBeanFactory;
import com.ivan.tinySpring.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * 实现 AOP 植入的关键类
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor,BeanFactoryAware {

    private AbstractBeanFactory beanFactory;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory=(AbstractBeanFactory) beanFactory;

    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
        //首先判断是否AspectJExpressionPointcutAdvisor的实例
        if (bean instanceof AspectJExpressionPointcutAdvisor){
            return bean;
        }
        //判断bean是否为方法拦截器
        if (bean instanceof MethodInterceptor){
            return bean;
        }
        //如果bean不是AspectJExpressionPointcutAdvisor的对象，则创建
        //在beanFactory根据类型创建相应的bean
        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.
                getBeanForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor:advisors){
            //判断是否为需要代理的对象，如果是则利用动态代理生成对象并返回
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())){
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());

                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean,bean.getClass(),bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                return advisedSupport.getProxy();
            }

        }
        return bean;
    }
}
