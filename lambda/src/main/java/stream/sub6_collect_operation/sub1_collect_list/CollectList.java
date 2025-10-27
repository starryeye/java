package stream.sub6_collect_operation.sub1_collect_list;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectList {

    /**
     * collect 연산에 대해 알아본다.
     *
     * Stream 객체를 collect 연산을 통해 다양한 Collection 객체로 변환시킬 수 있다.
     *
     * <R, A> R collect(Collector<? super T, A, R> collector)
     *      collect 연산의 파라미터로 Collector<T, A, R> 인터페이스의 구현체를 받아 스트림의 요소를 수집한다.
     *          T: 입력 요소의 타입
     *          A: 중간 누적 객체의 타입 (예: ArrayList)
     *          R: 최종 결과 타입 (예: List)
     *      Collector 를 직접 구현할 수 도 있지만, Java 가 제공하는 Collectors.XXX static 메서드를 사용하면 아주 편리하다.
     *
     * collect 연산 + Collectors 를 알아본다.
     *
     * 1. collect(Collectors.toList())
     *      가변 리스트(ArrayList)를 반환한다.
     *      반환된 리스트는 추가, 삭제 등 수정이 가능하다.
     * 2-1. collect(Collectors.toUnmodifiableList())
     *      불변 리스트(unmodifiable list)를 반환한다.
     *      반환된 리스트는 수정 시 UnsupportedOperationException 이 발생한다.
     *      collect 연산을 쓰지 않고 2-2번 toList() 로 하는 것과 동일하다.
     * 2-2. toList()
     *      간결하게 불변 리스트를 반환한다.
     *      collect(Collectors.toUnmodifiableList())와 동일한 효과.
     * 3. collect(Collectors.toCollection({생성자 참조 메서드 레퍼런스}))
     *      예제의 LinkedList::new 처럼 특정 리스트의 생성자 참조 메서드 레퍼런스를 이용하면
     *      해당 리스트로 반환해준다.
     *
     */

    public static void main(String[] args) {

        // 1. collect(Collectors.toList())
        List<String> list1 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toList());
        list1.add("EEE");
        System.out.println("list1.getClass() = " + list1.getClass()); // ArrayList
        System.out.println("list1 = " + list1);




        // 2-1. collect(Collectors.toUnmodifiableList())
        List<String> list2 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toUnmodifiableList());
//        list2.add("EEE"); // runtime error
        System.out.println("list2.getClass() = " + list2.getClass());
        System.out.println("list2 = " + list2);

        // 2-2. toList()
        List<String> list3 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .toList();
//        list3.add("EEE"); // runtime error
        System.out.println("list3.getClass() = " + list3.getClass());
        System.out.println("list3 = " + list3);



        // 3. collect(Collectors.toCollection({생성자 참조 메서드 레퍼런스}))
        LinkedList<String> list4 = Stream.of("AAA", "BBB", "CCC", "DDD")
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("list4.getClass() = " + list4.getClass());
        System.out.println("list4 = " + list4);
    }
}
