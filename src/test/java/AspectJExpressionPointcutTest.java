import com.ivan.tinySpring.aop.AspectJExpressionPointcut;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 整个表达式可以分为五个部分：
 *
 * 1、execution(): 表达式主体。
 *
 * 2、第一个*号：表示返回类型， *号表示所有的类型。
 *
 * 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
 *
 * 4、第二个*号：表示类名，*号表示所有的类。
 *
 * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
 */

public class AspectJExpressionPointcutTest {
    //测试类过滤器
    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* tinySpring.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloService.class);
        System.out.println(matches);
        Assert.assertTrue(matches);
    }

    //测试方法拦截器
    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* TimerInterceptor.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloServiceImpl.class.getDeclaredMethod("hello"),HelloServiceImpl.class);
        System.out.println(matches);
//        Assert.assertTrue(matches);
    }
}
