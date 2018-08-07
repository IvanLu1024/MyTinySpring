package com.ivan.tinySpring.context;

import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.beans.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements  ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    // TODO: 2018/8/7 对bean进行初始化工作，将bean注册到beanFactory中
    public void refresh() throws Exception{};

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {

    }
}
