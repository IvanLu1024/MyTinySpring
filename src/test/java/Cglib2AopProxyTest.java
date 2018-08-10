import com.ivan.tinySpring.aop.AdvisedSupport;
import com.ivan.tinySpring.aop.Cglib2AopProxy;
import com.ivan.tinySpring.aop.TargetSource;
import com.ivan.tinySpring.context.ApplicationContext;
import com.ivan.tinySpring.context.ClassPathXmlApplicationContext;
import org.junit.Test;


/**
 * @author yihua.huang@dianping.com
 */
public class Cglib2AopProxyTest {

	@Test
	public void testInterceptor() throws Exception {
		// --------- helloWorldService without AOP
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinySpring.xml");
		HelloService helloWorldService = (HelloService) applicationContext.getBean("helloService");
		helloWorldService.hello();

		// --------- helloWorldService with AOP
		// 1. 设置被代理对象(Joinpoint)
		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(helloWorldService, HelloServiceImpl.class,
				HelloService.class);
		advisedSupport.setTargetSource(targetSource);

		// 2. 设置拦截器(Advice)
		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		// 3. 创建代理(Proxy)
        Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
		HelloService helloWorldServiceProxy = (HelloService) cglib2AopProxy.getProxy();

		// 4. 基于AOP的调用
		helloWorldServiceProxy.hello();

	}
}
