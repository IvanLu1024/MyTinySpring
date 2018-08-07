package com.ivan.tinySpring.beans.beanDefinition;

/**
 * 从配置中读取BeanDefinitionReader
 */

public interface BeanDefinitionReader {

    // TODO: 2018/8/6 从配置文件中加载BeanDefinition
    void loadBeanDefinition(String location) throws Exception;
}
