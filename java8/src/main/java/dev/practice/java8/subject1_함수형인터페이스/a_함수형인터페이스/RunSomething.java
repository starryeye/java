package dev.practice.java8.subject1_함수형인터페이스.a_함수형인터페이스;

@FunctionalInterface // @FunctionalInterface 어노테이션을 적용하면 컴파일 단계에서 함수형인터페이스인지 검증해준다.
public interface RunSomething {
    /**
     * 함수형 인터페이스
     * - 추상 메서드를 하나만 가지고 있는 인터페이스이다. Single Abstract Method
     * - 다른 형태의 메서드가 몇개 있는지는 상관없다.
     */

    void doIt(); // interface 에서 메서드는 public abstract 키워드가 생략되어있다.


    //접근자는 암시적으로 public 이며, private 도 가능하다.
    static void printHello() {  // public 생략되어있다.
        System.out.println("Hello");
    }

    //접근자는 암시적으로 public 이며, public 만 가능하다. default 메서드는 접근자 default 와 다르다.
    default void printJava() {
        System.out.println("Java");
    }
}
