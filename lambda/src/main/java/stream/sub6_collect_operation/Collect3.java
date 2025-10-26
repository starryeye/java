package stream.sub6_collect_operation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collect3 {

    /**
     * collect 연산 + Collectors 를 알아본다.
     *
     * 1-1. collect(Collectors.toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper))
     *      HashMap 을 반환한다.
     *      요소를 받는 Function 람다 2개를 파라미터로 받으며 각각 key, value mapper 이다.
     * 1-2.
     * 1-3.
     * 2. collect(Collectors.toUnmodifiableMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper))
     *      불변 Map(unmodifiable map)을 반환한다.
     *      반환된 Map 은 수정 시 UnsupportedOperationException 이 발생한다.
     *      요소를 받는 Function 람다 2개를 파라미터로 받으며 각각 key, value mapper 이다.
     *
     *
     * 참고
     * collect(Collectors.toCollection({생성자 참조 메서드 레퍼런스})) 로 간단하게 Map 구현체를 반환받을 수 없고..
     * 1-3 을 참고해보자..
     */

    public static void main(String[] args) {

        // 1-1. collect(Collectors.toMap())
        Map<String, Integer> map1 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toMap(
                        s -> s,
                        s -> s.length()
                ));
        System.out.println("map1.getClass() = " + map1.getClass());
        System.out.println("map1 = " + map1);

        // 1-2
        // 1-3


        // 2. collect(Collectors.toUnmodifiableMap())
        Map<String, Integer> map2 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toUnmodifiableMap(
                        s -> s,
                        s -> s.length()
                ));
//        map2.put("EEE", 3); // runtime error
        System.out.println("map2.getClass() = " + map2.getClass());
        System.out.println("map2 = " + map2);



    }
}
