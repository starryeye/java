package dev.practice.java8;

@FunctionalInterface // @FunctionalInterface 어노테이션을 적용하면 컴파일 단계에서 함수형인터페이스인지 검증해준다.
public interface RunSomething {
    /**
     * 함수형 인터페이스
     * - 추상 메서드를 하나만 가지고 있는 인터페이스이다. Single Abstract Method
     * - 다른 형태의 메서드가 몇개 있는지는 상관없다.
     */

    void doIt(); // interface 에서 메서드는 public abstract 키워드가 생략되어있다.


    static void printHello() {  // public 생략되어있다.
        System.out.println("Hello");
    }

    default void printJava() {
        System.out.println("Java");
    }
}
