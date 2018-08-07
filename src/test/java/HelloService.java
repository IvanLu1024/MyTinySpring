public class HelloService {

    private String text;

    private Color color;

    public void hello(){
        System.out.println("Hello Service~"+text);
        System.out.println("颜色为："+color.getName());
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
