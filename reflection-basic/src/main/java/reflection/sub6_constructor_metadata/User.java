package reflection.sub6_constructor_metadata;

public class User {

    /**
     * reflection 예제에 사용되는 임의의 클래스이다.
     */

    private String name;
    private int age;

    public User() {
        System.out.println("User class default construct called..");
    }

    private User(String name) {
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

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
