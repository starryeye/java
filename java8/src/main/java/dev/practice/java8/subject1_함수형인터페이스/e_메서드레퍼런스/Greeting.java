package dev.practice.java8.subject1_함수형인터페이스.e_메서드레퍼런스;

public class Greeting {

    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    // 인스턴스 메서드
    public String hello(String name) {
        return "hello " + name;
    }

    // static 메서드
    public static String hi(String name) {
        return "hi " + name;
    }
}
