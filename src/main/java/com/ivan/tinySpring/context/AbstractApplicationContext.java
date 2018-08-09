package com.ivan.tinySpring.context;

import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.beans.beanDefinition.BeanPostProcessor;
import com.ivan.tinySpring.beans.factory.AbstractBeanFactory;

import java.util.List;

public abstract class AbstractApplicationContext implements  ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    // TODO: 2018/8/7 对bean进行初始化工作，将bean注册到beanFactory中
    // TODO: 2018/8/9 用于从资源文件加载类定义、扩展容器的功能 
    public void refresh() throws Exception{
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessor(beanFactory);
        onRefresh();

    }


    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    // TODO: 2018/8/9  获取所有的 BeanPostProcessor，并注册到 BeanFactory维护的 BeanPostProcessor 列表去。
    protected void registerBeanPostProcessor(AbstractBeanFactory abstractBeanFactory)throws Exception{
        List beanPostProcessors = abstractBeanFactory.getBeanForType(BeanPostProcessor.class);
        for (Object beanPostProcessor:beanPostProcessors){
            abstractBeanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    // TODO: 2018/8/9 以单例的方式，初始化所有 Bean
    protected void onRefresh()throws Exception{
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }


}
