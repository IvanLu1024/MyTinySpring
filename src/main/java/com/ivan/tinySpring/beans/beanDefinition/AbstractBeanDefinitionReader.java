package com.ivan.tinySpring.beans.beanDefinition;




import com.ivan.tinySpring.beans.beanDefinition.BeanDefinition;
import com.ivan.tinySpring.beans.beanDefinition.BeanDefinitionReader;
import com.ivan.tinySpring.beans.resource.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 从配置文件中获取BeanDefinition的抽象类
 *
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private Map<String,BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
        this.registry=new HashMap<String, BeanDefinition>();
        this.resourceLoader=resourceLoader;

    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
