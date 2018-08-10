public class HelloServiceImpl implements HelloService {

    private String text;
    private OutService outService;




    public void setText(String text) {
        this.text = text;
    }

    public void setOutService(OutService outService) {
        this.outService = outService;

    }

    @Override
    public void hello() {


            outService.output(text);


    }

}
