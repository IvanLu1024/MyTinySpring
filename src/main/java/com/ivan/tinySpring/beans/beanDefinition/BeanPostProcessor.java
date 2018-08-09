package com.ivan.tinySpring.beans.beanDefinition;

/**
 * 在bean初始化创建的时候拓展接口
 */
public interface BeanPostProcessor {
    //初始化之前的一些操作
    Object postProcessorBeforeInitialization(Object bean,String beanName) throws Exception;

    //初始化之后的一些操作
    Object postProcessorAfterInitialization(Object bean,String beanName) throws Exception;
}
