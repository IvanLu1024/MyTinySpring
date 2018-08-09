import com.ivan.tinySpring.beans.beanDefinition.BeanPostProcessor;

public class BeanInitializeLogger implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("初始化之前:"+beanName);
        System.out.println("----------------");

        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("初始化之后："+beanName);
        System.out.println("-----------------------");

        return bean;
    }
}
