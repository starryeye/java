package dev.practice.java8.bb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {

    /**
     * Iterable 의 기본 메서드
     * - forEach()
     * - spliterator()
     *
     * Collection (Iterable 을 상속 받음) 의 기본 메서드
     * - stream() / parallelStream()
     * - removeIf(Predicate)
     * - spliterator()
     *
     * Comparator 의 기본 메서드 및 스태틱 메서드
     * - reversed()
     * - thenComparing()
     * - static reverseOrder() / naturalOrder()
     * - static nullsFirst() / nullsLast()
     * - static comparing()
     *
     * 참고
     * - https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html
     * - https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html
     * - https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html
     * - https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
     */

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("Java");
        names.add("Spring");
        names.add("JPA");
        names.add("Redis");

        /**
         * foreEach
         * foreEach 는 Iterable 인터페이스의 default 메서드이다.
         * forEach 를 사용 하면 각각의 element 들을 손쉽게 순회할 수 있다.
         * forEach 는 파라미터로 Consumer 를 받는다.
         */
        names.forEach(System.out::println); // 각 element 를 System.out.println 의 파라미터로 전달한다.

        // --

        /**
         * spliterator
         * 쪼갤수 있는 기능을 가진 Iterator 라 생각하면 편하다.
         */
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> stringSpliterator = spliterator.trySplit(); //trySplit 을 이용하여 반으로 쪼갰다.
        while (spliterator.tryAdvance(System.out::println)); //순회하면서 출력
        System.out.println("=================");
        while (stringSpliterator.tryAdvance(System.out::println)); //순회하면서 출력

        // --

        /**
         * stream
         * Collection 을 상속 받은 클래스는 전부 이용가능한 메서드이다.
         * stream 메서드는 내부에 spliterator 를 이용하여 구현되어있다.
         *
         * element 를 stream 으로 만들어 Functional 하게 처리할 수 있게한다.
         */
        long countJ = names.stream()
                .map(String::toUpperCase) // element 를 toUpperCase 처리
                .filter(s -> s.startsWith("J")) // 필터링
                .count(); // element 갯수를 세아린다.
        System.out.println(countJ);

        // --

        /**
         * boolean removeIf(Predicate<E> p)
         * 원본에서 조건을 만족하는 원소를 뺀다.
         */
        names.removeIf(s -> s.startsWith("S"));
        names.forEach(System.out::println);

        // --

        /**
         * Comparator 의 reversed
         */
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());



    }
}
