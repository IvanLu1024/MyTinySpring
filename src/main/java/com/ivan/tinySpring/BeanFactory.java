package com.ivan.tinySpring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口，标识一个 IoC 容器。通过 getBean(String) 方法来 获取一个对象
 *
 */

public class BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String, BeanDefinition>();

    // TODO: 2018/8/6 获取Bean
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    // TODO: 2018/8/6 注册BeanDefinition
    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        this.beanDefinitionMap.put(name,beanDefinition);


    }
}
