package stream.sub6_collect_operation.sub3_collect_map;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectMap {

    /**
     * collect 연산 + Collectors 를 알아본다.
     *
     * 1-1. collect(Collectors.toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper))
     *      HashMap 을 반환한다.
     *      요소를 받는 Function 람다 2개를 파라미터로 받으며 각각 key, value mapper 이다.
     *      HashMap 을 생성하는 도중에 중복 key 가 발생할 경우, IllegalStateException 예외가 발생한다.
     * 1-2. Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction)
     *      1-1 과 동일한데..
     *      3 번째 파라미터로 중복 key 가 발생할 경우 어떻게 처리를 할지에 대해 BinaryOperator 를 받기 때문에
     *          중복 key 가 발생하더라도 1-1 처럼 예외가 발생하지 않는다.
     * 1-3. Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper, extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapFactory)
     *      1-2 와 동일한데..
     *      4 번째 파라미터로 어떤 Map 구현체로 생성할지 명시할 수 있어서
     *      HashMap 이 아닌 다른 타입으로 생성이 가능하다.
     *
     *
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

        // 1-2. collect(Collectors.toMap())
        Map<String, Integer> map2 = Stream.of("AAA", "AAA", "BBB", "CCC") // 중복 key 가 발생!!
                .collect(Collectors.toMap(
                        s -> s,
                        s -> s.length(),
                        (originVal, newVal) -> originVal + newVal // 중복 key 가 발생할 경우, 기존 키의 값에 새로운 값을 더하는 것으로 해결
                ));
        System.out.println("map2.getClass() = " + map2.getClass());
        System.out.println("map2 = " + map2);

        // 1-3.collect(Collectors.toMap())
        TreeMap<String, Integer> map3 = Stream.of("CCC", "AAA", "DDD", "BBB")
                .collect(Collectors.toMap(
                        s -> s,
                        s -> s.length(),
                        (originVal, newVal) -> originVal + newVal,
                        TreeMap::new // TreeMap 으로 생성하여 key 를 기준으로 정렬됨.
                ));
        System.out.println("map3.getClass() = " + map3.getClass());
        System.out.println("map3 = " + map3);


        // 2. collect(Collectors.toUnmodifiableMap())
        Map<String, Integer> map4 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toUnmodifiableMap(
                        s -> s,
                        s -> s.length()
                ));
//        map4.put("EEE", 3); // runtime error
        System.out.println("map4.getClass() = " + map4.getClass());
        System.out.println("map4 = " + map4);



    }
}
