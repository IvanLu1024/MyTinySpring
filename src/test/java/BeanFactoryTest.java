import com.ivan.tinySpring.BeanDefinition;
import com.ivan.tinySpring.PropertyValue;
import com.ivan.tinySpring.PropertyValues;
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
       /* //1.初始化BeanFactory
        BeanFactory beanFactory=new AutowireCapableBeanFactory();

        //2.注入bean
        BeanDefinition beanDefinition=new BeanDefinition();
        beanDefinition.setBeanClassName("HelloService");
        beanFactory.registerBeanDefinition("helloService",beanDefinition);


        //3.获取bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.hello(" 赵黎");*/


    }

    /**
     * 这一步，我们想要为bean注入属性。我们选择将属性注入信息保存成PropertyValue对象，并且保存到BeanDefinition中。
     * 这样在初始化bean的时候，我们就可以根据PropertyValue来进行bean属性的注入。
     * Spring本身使用了setter来进行注入，这里为了代码简洁，我们使用Field的形式来注入。
     *
     */

    @Test
    public void test3(){
        //1.初始化BeanFactory
        BeanFactory beanFactory=new AutowireCapableBeanFactory();

        //2.注入bean
        BeanDefinition beanDefinition=new BeanDefinition();
        beanDefinition.setBeanClassName("HelloService");

        //3.设置属性
        PropertyValues propertyValues=new PropertyValues();
        propertyValues.addPropertyValue(new  PropertyValue("text","鲁志鹏"));
        beanDefinition.setPropertyValues(propertyValues);

        //4.生成bean
        try {
            beanFactory.registerBeanDefinition("helloService",beanDefinition);

            //5.获取bean
            HelloService helloService = (HelloService) beanFactory.getBean("helloService");
            helloService.hello();
        }catch (Exception e)
        {
            e.printStackTrace();
        }




    }
}
