package dev.practice.java8.subject1.e;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {

        /**
         * 메서드 레퍼런스 사용
         * 아래 두 함수형 인터페이스 구현체는 동일하다. (21, 22 line)
         *
         * 메서드 레퍼런스
         * 람다 표현식을 구현할 때, 사용할 수 있는 방법이다.
         * 기존에 있는 메서드를 함수형 인터페이스의 구현체로 쓰는 것이다.
         */
        UnaryOperator<String> hi = (s) -> "hi " + s;
        UnaryOperator<String> hi2 = Greeting::hi; //static 메서드 레퍼런스 사용
        System.out.println(hi.apply("spring"));
        System.out.println(hi2.apply("spring"));

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello; //인스턴스 메서드 레퍼런스 사용
        System.out.println(hello.apply("world"));

        Supplier<Greeting> newGreeting = Greeting::new; //생성자도 메서드 레퍼런스로 가능하다. (파라미터가 없는 생성자를 사용한다.)
        Greeting greeting2 = newGreeting.get();

        Function<String, Greeting> javaGreeting = Greeting::new; //파라미터가 하나있는 생성자를 메서드 레퍼런스로 사용한다.
        Greeting greeting3 = javaGreeting.apply("java");


        /**
         * 임의의 객체의 인스턴스 메서드 참조에 대해 알아보자.
         */
        String[] names = {"Spring", "Java", "JPA"};

        // 익명 클래스를 이용하여 comparator 를 구현하여 사용
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        /**
         * 참고로 Comparator 는 함수형 인터페이스이다. (Java 8 부터 함수형 인터페이스로 변경됨)
         * 내부에 보면.. int compare(T o1, T o2); 로 추상 메서드가 단 하나만 존재한다.
         * boolean equals(Object obj); 이것은 default 메서드가 아닌데 어떻게 가능하지..?
         * -> Object 에 있는 메서드로 대상이 아닌듯..
         */

        // 함수형 인터페이스는 람다를 이용하여 구현가능하다. comparator 구현
        Arrays.sort(names, (o1, o2) -> 0);

        // 람다를 쓸 수 있다는 말은 메서드 레퍼런스도 사용하능하다.!
        Arrays.sort(names, String::compareToIgnoreCase);
        /**
         * String 의 compareToIgnoreCase 는 static 메서드가 아니다.!!
         * 인스턴스 메서드이다... 엥?
         * ...
         * 동작 순서 부터 알아보겠다..
         * "Spring" 이 "Java" 를 파라미터로 받아서 compareToIgnoreCase 함수를 수행한다. (인트 반환)
         * "Java" 가 "JPA" 를 파라미터로 받아서 compareToIgnoreCase 함수를 수행한다. (인트 반환)
         *
         * 따라서, "Spring", "Java" -> 임의의 객체의 인스턴스를 거쳐가며 compareToIgnoreCase 라는 인스턴스 메서드를 사용하게 되는 것이다.
         */

        /**
         * 정리
         *
         * 스태틱 메서드 참조
         * - 타입::스태틱 메서드
         *
         * 특정 객체의 인스턴스 메서드 참조
         * - 객체 레퍼런스::인스턴스 메서드
         *
         * 임의 객체의 인스턴스 메소드 참조
         * - 타입::인스턴스 메서드
         *
         * 생성자 참조
         * - 타입::new
         *
         * 레퍼런스란.. 인스턴스의 변수명..
         */
    }
}
