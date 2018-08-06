import com.ivan.tinySpring.BeanDefinition;
import com.ivan.tinySpring.BeanFactory;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void test1(){
        //1.初始化BeanFactory
        BeanFactory beanFactory=new BeanFactory();


        //2.注入bean
        BeanDefinition beanDefinition=new BeanDefinition(new HelloService());
        beanFactory.registerBeanDefinition("helloService",beanDefinition);


        //3.获取bean
        HelloService helloService = (HelloService)beanFactory.getBean("helloService");
        helloService.hello("Ivan");
    }
}
