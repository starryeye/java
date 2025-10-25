package stream.sub6_collect_operation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collect1 {

    /**
     * collect 연산에 대해 알아본다.
     *
     * Stream 객체를 collect 연산을 통해 다양한 Collection 객체로 변환시킬 수 있다.
     *
     * <R, A> R collect(Collector<? super T, A, R> collector)
     *      collect 연산의 파라미터로 Collector<T, A, R> 인터페이스의 구현체를 받는다.
     *      Collector 를 직접 구현할 수 도 있지만, Java 가 제공하는 Collectors.XXX static 메서드를 사용하면 아주 편리하다.
     *
     * collect 연산 + Collectors 를 알아본다.
     *
     * 1. Collectors.toList()
     *      mutable list
     * 2. Collectors.toUnmodifiableList()
     *      immutable list
     *      collect 연산을 쓰지 않고 toList() 로 하는 것과 동일하다.
     */

    public static void main(String[] args) {

        // collect(Collectors.toList())
        List<String> list1 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toList());
        list1.add("EEE");
        System.out.println("list1 = " + list1);


        // collect(Collectors.toUnmodifiableList())
        List<String> list2 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toUnmodifiableList());
//        list2.add("EEE"); // runtime error
        System.out.println("list2 = " + list2);
    }
}
