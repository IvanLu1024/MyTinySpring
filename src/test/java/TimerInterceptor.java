import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义拦截器--计时器
 */
public class TimerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long time = System.nanoTime();
		System.out.println("拦截器方法 " + invocation.getMethod().getName() + " 启动");
		Object proceed = invocation.proceed();
		System.out.println("拦截器方法 " + invocation.getMethod().getName() + " 结束! 花费了 " + (System.nanoTime() - time)
				+ "纳秒");
		return proceed;
	}
}

