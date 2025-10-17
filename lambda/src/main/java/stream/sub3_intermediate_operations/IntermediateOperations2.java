package stream.sub3_intermediate_operations;

import java.util.Collection;
import java.util.List;

public class IntermediateOperations2 {

    /**
     * 중간 연산자에 대해 알아본다.
     *
     * 1. flatMap
     *      <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
     *          각 요소(하나의 요소는 Collector)를 Stream 으로 변환하고, 각 Stream 의 요소들을 하나의 Stream 으로 평탄화(flatten)한다.
     *              즉, Collection 안의 Collection 구조와 같은 중첩 구조를 1차원으로 펼치는데 사용할 수 있다.
     *          파라미터를 보면..
     *              Function 으로 T -----> ? extends Stream<? extends R> 인것을 볼 수 있다.
     *              각 요소가 파라미터 T 로 전달되면 Stream 을 응답하는 Function 을 넣어줘야 하는 것..
     *                  이렇게 넣어주면, flatMap 결과로는 Stream<R> 로 응답 되므로.. 머리속으로 생각하면 평탄화 되겠구나.. 라고 생각할 수 있다.
     */

    public static void main(String[] args) {

        List<List<Integer>> origin = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5, 6)
        );
        System.out.println("origin = " + origin);


        List<Integer> result = origin.stream()
                .flatMap(Collection::stream) // 각 요소(컬렉션)를 stream 으로 변환하는 Function 을 파라미터로 제공
                .toList();

        System.out.println("flatMap result = " + result);
    }
}
