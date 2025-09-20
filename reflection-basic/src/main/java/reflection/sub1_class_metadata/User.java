package reflection.sub1_class_metadata;

public class User {

    /**
     * reflection 예제에 사용되는 임의의 클래스이다.
     */

    private String name;
    private int age;

    public User() {
        System.out.println("User class default construct called..");
    }

    public User(String name) {
        this.name = name;
        System.out.println("User class construct called.. name: " + name);
    }

    public void changeName(String name) {
        this.name = name;
        System.out.println("User.changeName method called.. name: " + name);
    }

    public String getGreet(String greeting) {
        System.out.println("User.getGreet method called..");
        return greeting + ", " + name;
    }

    private void privateMethod() {
        System.out.println("User.privateMethod method called..");
    }

    void defaultMethod() {
        System.out.println("User.defaultMethod() called..");
    }

    protected void protectedMethod() {
        System.out.println("User.protectedMethod() called..");
    }
}
