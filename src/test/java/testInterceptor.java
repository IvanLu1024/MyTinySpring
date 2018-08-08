
import com.ivan.tinySpring.aop.AdvisedSupport;
import com.ivan.tinySpring.aop.AopProxy;
import com.ivan.tinySpring.aop.JdkDynamicAopProxy;
import com.ivan.tinySpring.aop.TargetSource;
import com.ivan.tinySpring.context.ApplicationContext;
import com.ivan.tinySpring.context.ClassPathXmlApplicationContext;
import org.junit.Test;


public class testInterceptor {
    @Test
    public void test7()throws Exception{
        //不使用AOP的容器创建
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("tinySpring.xml");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.hello();

        //使用AOP的容器创建
        // 1. 设置被代理对象(Joinpoint)--Joinpoint则代表切点，将被代理对象封装到切点中
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloService, HelloService.class);
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器(Advice)，并且将拦截器--Advice定义了在切点指定的逻辑
        TimerInterceptor timerInterceptor=new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 创建代理(Proxy)--织入点
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloService proxy = (HelloService) jdkDynamicAopProxy.getProxy();

        // 4. 基于AOP的调用
        System.out.println("--------------------------");
        proxy.hello();

    }
}
