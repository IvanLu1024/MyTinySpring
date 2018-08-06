package com.ivan.tinySpring.factory;

import com.ivan.tinySpring.BeanDefinition;
import com.ivan.tinySpring.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可以自动装配内容的BeanFactory
 */

public class AutowireCapableBeanFactory extends AbstractBeanFactory {


    //bean的初始化
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            Object bean = createBeanInstance(beanDefinition);
            applyPropertyValues(bean,beanDefinition);
            return bean;

        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    // TODO: 2018/8/6 将bean实例化
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return beanDefinition.getBeanClass().newInstance();


    }

    // TODO: 2018/8/6 注入所有属性
    protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception{
        for (PropertyValue propertyValue:beanDefinition.getPropertyValues().getPropertyValues()){
            Field declaredField=bean.getClass().getDeclaredField(propertyValue.getName());

            declaredField.setAccessible(true);
            declaredField.set(bean,propertyValue.getValue());


        }


    }
}
