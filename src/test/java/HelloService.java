public class HelloService {

    private String text;

    public void hello(){
        System.out.println("Hello Service~"+text);
    }

    public void setText(String text) {
        this.text = text;
    }
}
