import com.ivan.tinySpring.BeanDefinition;
import com.ivan.tinySpring.factory.AutowireCapableBeanFactory;
import com.ivan.tinySpring.factory.BeanFactory;
import org.junit.Test;

public class BeanFactoryTest {

    /**
     * IoC最基本的角色有两个：容器(BeanFactory)和Bean本身。
     * 这里使用BeanDefinition来封装了bean对象，这样可以保存一些额外的元信息。
     */
    @Test
    public void test1(){
        /*//1.初始化BeanFactory
        BeanFactory beanFactory=new BeanFactory();


        //2.注入bean
        BeanDefinition beanDefinition=new BeanDefinition(new HelloService());
        beanFactory.registerBeanDefinition("helloService",beanDefinition);


        //3.获取bean
        HelloService helloService = (HelloService)beanFactory.getBean("helloService");
        helloService.hello("Ivan");*/
    }

    /**
     * step1中的bean是初始化好之后再set进去的，实际使用中，我们希望容器来管理bean的创建。
     * 于是我们将bean的初始化放入BeanFactory中。
     * 为了保证扩展性，我们使用Extract Interface的方法，将BeanFactory替换成接口，
     * 而使用AbstractBeanFactory和AutowireCapableBeanFactory作为其实现。
     * "AutowireCapable"的意思是“可自动装配的”，为我们后面注入属性做准备。
     */
    @Test
    public void test2(){
        //1.初始化BeanFactory
        BeanFactory beanFactory=new AutowireCapableBeanFactory();

        //2.注入bean
        BeanDefinition beanDefinition=new BeanDefinition();
        beanDefinition.setBeanClassName("HelloService");
        beanFactory.registerBeanDefinition("helloService",beanDefinition);


        //3.获取bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.hello(" 赵黎");


    }
}
