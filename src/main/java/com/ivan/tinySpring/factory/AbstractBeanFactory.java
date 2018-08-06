package com.ivan.tinySpring.factory;

import com.ivan.tinySpring.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *BeanFactory的抽象类
 *
 */

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String, BeanDefinition>();


    @Override
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name,beanDefinition);


    }

    /**
     * 初始化bean
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition);
}
