package dev.practice.java8.three;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {

        /**
         * https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
         * 자바에서 미리 정의해둔 자주 사용할만한 함수 인터페이스
         *
         * - Function<T, R>
         *     T 를 받아서 R 리턴
         *     R apply(T t)
         *     compose, andThen
         *
         * - BiFunction<T, U, R>
         *     T, U 를 받아서 R 리턴
         *     R apply(T t, U u)
         *
         * - Consumer<T>
         *     T 를 받고 리턴 없음
         *     void accept(T t)
         *     andThen
         *
         * - Supplier<T>
         *     아무것도 받지 않고 T 리턴
         *     T get()
         *
         * - Predicate<T>
         *     T 를 받고 boolean 을 리턴
         *     boolean test(T t)
         *     and, or, negate
         *
         * - UnaryOperator<T>
         *     Function<T, R> 을 상속받음, T 를 받고 T 를 리턴, 즉 입력 타입과 반환 타입이 동일하다.
         *     T identity(T t)
         *
         * - BinaryOperator<T>
         *     BiFunction<T, U, R> 을 상속받음, T, T 를 받고 T 를 리턴, 즉 2개의 입력 타입과 반환 타입이 모두 동일하다.
         *     T apply(T t1, T t2)
         */

        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        // 람다 표현식을 사용하면 함수형 인터페이스의 인스턴스를 만들수 있다.
        Function<Integer, Integer> plus20 = integer -> integer + 20;
        System.out.println(plus20.apply(1));

        // 자바에서 함수는 고차 함수의 특성을 가지므로 함수 조합이 가능하다.
        // Function<T, R> 은 default 메서드로 조합용 메서드를 제공한다. (compose, andThen)
        Function<Integer, Integer> multiply2 = integer -> integer * 2;
        // 입력 -> 입력값 * 2 -> 반환값 + 10 -> 반환, (T * 2 + 10 = R)
        Function<Integer, Integer> multiply2AndPlus10 = plus20.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));
        // (T + 10) * 2 = R
        System.out.println(plus20.andThen(multiply2).apply(2));

        // --

        Consumer<Integer> printInteger = i -> System.out.println(i); // 메서드 레퍼런스를 사용하라고 추천 중이네..
        printInteger.accept(10);

        // --

        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // --

        Predicate<String> startWithHello = (s) -> s.startsWith("hello");
        Predicate<Integer> isOdd = (i) -> i % 2 == 1;

        // --

        UnaryOperator<Integer> plus30 = (i) -> i + 30;

        // --

        BinaryOperator<Integer> plus = (i1, i2) -> i1 + i2; // 메서드 레퍼런스를 쓰라고 추천중이다.
    }
}
