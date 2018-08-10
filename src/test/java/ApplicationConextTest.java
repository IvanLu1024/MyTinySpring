import com.ivan.tinySpring.aop.AspectJExpressionPointcutAdvisor;
import com.ivan.tinySpring.context.ApplicationContext;
import com.ivan.tinySpring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApplicationConextTest {
    @Test
    public void testAutoAspectJ()throws  Exception{
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("tinySpring.xml");


        //System.out.println("-----");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.hello();
    }

    @Test
    public void testBeanPostProcessor()throws  Exception{
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("tinySpring-beanPostProcessor.xml");
        HelloService helloService = (HelloServiceImpl) applicationContext.getBean("helloService");
        helloService.hello();
    }
}
