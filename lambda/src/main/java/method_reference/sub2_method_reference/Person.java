package method_reference.sub2_method_reference;

public class Person {

    private String name;

    // 기본 생성자
    public Person() {
        this("Unknown");
    }

    // 매개변수가 존재하는 생성자
    public Person(String name) {
        this.name = name;
    }

    // 정적 메서드
    public static String greeting() {
        return "Hello";
    }

    // 매개변수가 존재하는 정적 메서드
    public static String greetingWithName(String name) {
        return "Hello " + name;
    }

    // 인스턴스 메서드
    public String introduce() {
        return "I am " + name;
    }

    // 매개변수가 존재하는 인스턴스 메서드
    public String introduceWithNumber(int number) {
        return "I am " + name + ", my number is " + number;
    }

    public String getName() {
        return name;
    }
}
