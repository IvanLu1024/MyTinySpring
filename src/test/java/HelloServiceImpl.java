public class HelloServiceImpl implements HelloService {
    private Color color;
    private String text;


    @Override
    public void hello() {

            System.out.println("Hello Service~"+text);
            System.out.println("颜色为："+color.getName());

    }
}
