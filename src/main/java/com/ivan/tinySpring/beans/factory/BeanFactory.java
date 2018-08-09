package com.ivan.tinySpring.beans.factory;

import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;

/**
 * 接口，标识一个 IoC 容器。通过 getBean(String) 方法来 获取一个对象
 *
 */

public interface BeanFactory {



    // TODO: 2018/8/6 获取Bean
    public Object getBean(String name) throws Exception;


}
