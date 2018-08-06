package com.ivan.tinySpring.factory;

import com.ivan.tinySpring.BeanDefinition;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {


    //bean的初始化
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            return bean;

        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return null;

    }
}
