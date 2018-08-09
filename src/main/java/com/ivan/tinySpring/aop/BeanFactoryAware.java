package com.ivan.tinySpring.aop;

import com.ivan.tinySpring.beans.factory.BeanFactory;

/**
 *
 * 实现该接口的就可以实现Bean获取对容器操作的权限，也就允许了编写扩展 IoC容器的功能的 Bean。
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory)throws Exception;
}
