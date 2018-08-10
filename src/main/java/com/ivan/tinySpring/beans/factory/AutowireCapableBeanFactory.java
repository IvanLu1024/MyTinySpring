package com.ivan.tinySpring.beans.factory;

import com.ivan.tinySpring.aop.BeanFactoryAware;
import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.BeanReference;
import com.ivan.tinySpring.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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




    // TODO: 2018/8/6 注入所有属性
    protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception{
        //首先判断这个bean是否有操作容器的权限
        if (bean instanceof BeanFactoryAware){
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        
        
        for (PropertyValue propertyValue:beanDefinition.getPropertyValues().getPropertyValues()){
           /* //获取类或接口的指定已声明字段
            Field declaredField=bean.getClass().getDeclaredField(propertyValue.getName());

            //获取私有属性的时候必须先设置Accessible为true，才能获取
            declaredField.setAccessible(true);
            //设置属性*/
            Object value = propertyValue.getValue();
            
            if (value instanceof BeanReference){
                BeanReference beanReference=(BeanReference)value;
                
                value=getBean(beanReference.getName());
            }
            // TODO: 2018/8/9  
            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                //e.printStackTrace();

                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }
}
