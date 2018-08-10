package com.ivan.tinySpring.beans.factory;

import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.beans.beanDefinition.BeanPostProcessor;

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

    //Step5 存放所有的beanDefinition名字的列表--缓存
    private final List<String> beanDefinitionNames=new ArrayList<String>();

    //存放BeanPostProcessor的列表--缓存
    private  List<BeanPostProcessor> beanPostProcessors=new ArrayList<BeanPostProcessor>();


    // TODO: 2018/8/7 获取 Bean 时，如果 Bean 已经存在于容器中，则返回之，否则则调用 doCreateBean 方法装配一个 Bean
    @Override
    public Object getBean(String name)throws Exception {
        BeanDefinition beanDefinition=beanDefinitionMap.get(name);
        if (beanDefinition==null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");

        }
        Object bean = beanDefinition.getBean();
        //如果缓存中没有bean就创建这个bean
        if (bean==null){

            bean=doCreateBean(beanDefinition);

            bean=initializeBean(bean,name);
        }
        //bean=initializeBean(bean,name);
        return bean;

    }

    /**
     * 利用BeanPostProcessor初始化bean
     * @param bean
     * @param beanName
     * @return
     */
    protected Object initializeBean(Object bean,String beanName) throws Exception{
        // TODO: 2018/8/9 初始化之前的操作
        for (BeanPostProcessor postProcessor:beanPostProcessors){
            postProcessor.postProcessorBeforeInitialization(bean,beanName);
        }
        // TODO: 2018/8/9 初始化之后的操作
        for (BeanPostProcessor postProcessor:beanPostProcessors){
            postProcessor.postProcessorAfterInitialization(bean,beanName);
        }
        return bean;


    }


    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
        // TODO: 2018/8/7 将所需要注册的BeanDefinition放入缓存中
        beanDefinitionMap.put(name,beanDefinition);

        beanDefinitionNames.add(name);
       /* Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);*/


    }

    /**
     * 通过类型获取bean的实例
     * @param type
     * @return
     * @throws Exception
     */
    public List getBeanForType(Class type)throws Exception{
        List beans=new ArrayList<Object>();
        for (String beanDefinitionName:beanDefinitionNames){
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;


    }

    /**
     * 添加BeanPostProcessor
     * @param beanPostProcessor
     */
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
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
    protected  Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
        Object beanInstance = createBeanInstance(beanDefinition);
        beanDefinition.setBean(beanInstance);
        applyPropertyValues(beanInstance,beanDefinition);
        return beanInstance;

    };



    /**
     * 注入属性
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }

    /**
     * 创建类的实例
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition)throws Exception{
        return beanDefinition.getBeanClass().newInstance();
    }
}
