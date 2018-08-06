package com.ivan.tinySpring;

/**
 * bean的内容及元数据，保存在BeanFactory中，包装bean的实体
 *
 */

public class BeanDefinition {
    private Object bean;

    private Class beanClass;

    private String beanClassName;

    // TODO: 2018/8/6 必须初始化 ！！！
    private PropertyValues propertyValues=new PropertyValues();

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public BeanDefinition(){}

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    // TODO: 2018/8/6 捕获类型无法找到的异常
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try{
            this.beanClass=Class.forName(beanClassName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;

    }
}
