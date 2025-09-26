package annotation.sub5_practice.handler_mapping;

public class SimpleController {

    @SimpleMapping(value = "/")
    public void home() {
        System.out.println("home controller called");
    }

    @SimpleMapping(value = "/page1")
    public void page1() {
        System.out.println("page1 controller called");
    }
}
