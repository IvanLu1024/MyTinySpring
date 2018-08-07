package com.ivan.tinySpring.factory;

import com.ivan.tinySpring.BeanDefinition;
import com.ivan.tinySpring.BeanReference;
import com.ivan.tinySpring.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可以自动装配内容的BeanFactory
 */

public class AutowireCapableBeanFactory extends AbstractBeanFactory {


    //bean的初始化
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {

            Object bean = createBeanInstance(beanDefinition);

            beanDefinition.setBean(bean);

            applyPropertyValues(bean,beanDefinition);
            return bean;




    }

    // TODO: 2018/8/6 将bean实例化
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return beanDefinition.getBeanClass().newInstance();


    }

    // TODO: 2018/8/6 注入所有属性
    protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception{
        for (PropertyValue propertyValue:beanDefinition.getPropertyValues().getPropertyValues()){
            //获取类或接口的指定已声明字段
            Field declaredField=bean.getClass().getDeclaredField(propertyValue.getName());

            //获取私有属性的时候必须先设置Accessible为true，才能获取
            declaredField.setAccessible(true);
            //设置属性
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference){
                BeanReference beanReference=(BeanReference)value;
                
                value=getBean(beanReference.getName());
            }
            declaredField.set(bean,value);


        }


    }
}
