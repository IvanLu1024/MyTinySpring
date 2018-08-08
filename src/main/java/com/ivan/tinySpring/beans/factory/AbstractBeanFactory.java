package com.ivan.tinySpring.beans.factory;

import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *BeanFactory的抽象类
 *
 *
 */

public abstract class AbstractBeanFactory implements BeanFactory {

    //用于保存类定义信息
    private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String, BeanDefinition>();

    //Step5 存放所有的beanDefinition名字的列表
    private final List<String> beanDefinitionNames=new ArrayList<String>();


    // TODO: 2018/8/7 获取 Bean 时，如果 Bean 已经存在于容器中，则返回之，否则则调用 doCreateBean 方法装配一个 Bean
    @Override
    public Object getBean(String name)throws Exception {
        BeanDefinition beanDefinition=beanDefinitionMap.get(name);
        if (beanDefinition==null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");

        }
        Object bean = beanDefinition.getBean();
        if (bean==null){

            bean=doCreateBean(beanDefinition);
        }
        return bean;

    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
        // TODO: 2018/8/7 将所需要注册的BeanDefinition放入缓存中
        beanDefinitionMap.put(name,beanDefinition);

        beanDefinitionNames.add(name);
       /* Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);*/


    }

    public void preInstantiateSingletons()throws Exception{
        for (Iterator it=this.beanDefinitionNames.iterator();it.hasNext();){
            String beanName = (String) it.next();
            getBean(beanName);
        }

    }

    /**
     * 初始化bean
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
