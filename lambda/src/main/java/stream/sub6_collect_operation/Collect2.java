package stream.sub6_collect_operation;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collect2 {

    /**
     * collect 연산 + Collectors 를 알아본다.
     *
     * 1. collect(Collectors.toSet())
     *      HashSet 을 반환한다.
     * 2. collect(Collectors.toUnmodifiableSet())
     *      불변 리스트(unmodifiable list)를 반환한다.
     *      반환된 Set은 수정 시 UnsupportedOperationException 이 발생한다.
     * 3. collect(Collectors.toCollection({생성자 참조 메서드 레퍼런스}))
     *      예제의 TreeSet::new 처럼 특정 리스트의 생성자 참조 메서드 레퍼런스를 이용하면
     *      해당 Set 으로 반환해준다.
     */

    public static void main(String[] args) {

        // 1. collect(Collectors.toSet())
        Set<String> set1 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toSet());
        System.out.println("set1.getClass() = " + set1.getClass());
        System.out.println("set1 = " + set1);


        // 2. collect(Collectors.toUnmodifiableSet())
        Set<String> set2 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toUnmodifiableSet());
//        set2.add("EEE"); // runtime error
        System.out.println("set2.getClass() = " + set2.getClass());
        System.out.println("set2 = " + set2);


        // 3. collect(Collectors.toCollection({생성자 참조 메서드 레퍼런스}))
        Set<String> set3 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("set3.getClass() = " + set3.getClass());
        System.out.println("set3 = " + set3);


    }
}
