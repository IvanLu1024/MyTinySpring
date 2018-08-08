package com.ivan.tinySpring.beans.factory;

import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.BeanReference;
import com.ivan.tinySpring.beans.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可以自动装配内容的BeanFactory
 *
 */

public class AutowireCapableBeanFactory extends AbstractBeanFactory {


    /**
     * 1，通过 BeanDefinition 中保存的类信息实例化一个对象；
     * 2，把对象保存在 BeanDefinition 中，以备下次获取；
     * 3，为其装配属性。装配属性时，通过 BeanDefinition 中维护的 PropertyValues 集合类，
     * 把 String - Value 键值对注入到 Bean 的属性中去。
     * 如果 Value 的类型是 BeanReference 则说明其是一个引用（对应于XML中的 ref），
     * 通过 getBean 对其进行获取，然后注入到属性中。
     * @param beanDefinition
     * @return
     * @throws Exception
     */

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
