package stream.sub4_terminal_operations;

import java.util.List;
import java.util.Optional;

public class TerminalOperations1 {

    /**
     * 최종 연산자에 대해 알아본다.
     *
     * 1. forEach
     *      void forEach(Consumer<? super T> action);
     *          각 요소에 대해 특정 동작을 수행 (반환값 없음)
     * 2. count
     *      long count();
     *          모든 요소의 갯수 반환
     * 3. reduce
     *      Optional<T> reduce(BinaryOperator<T> accumulator);
     *          누적 함수를 사용하여 모든 요소를 단일 결과로 만들어 Optional 로 반환
     *      T reduce(T identity, BinaryOperator<T> accumulator);
     *          누적 함수를 사용하여 모든 요소를 단일 결과로 만들어 반환 (초깃값 존재)
     * 4. min
     *      Optional<T> min(Comparator<? super T> comparator);
     *          모든 요소에 대해 최솟값 Optional 반환
     * 5. max
     *      Optional<T> max(Comparator<? super T> comparator);
     *          모든 요소에 대해 최댓값 Optional 반환
     */

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10);

        // forEach
        System.out.println("1. forEach - 각 요소에 대해 순차적으로 특정 작업을 수행");
        numbers.stream()
                .forEach(n -> System.out.print(n + " ")); // 여기선 출력
        System.out.println();


        // count
        System.out.println("2. count - 요소 갯수를 반환");
        long count = numbers.stream()
                .filter(number -> number % 2 == 0) // 여기선 짝수 요소 갯수
                .count();
        System.out.println("even number count = " + count);


        // reduce
        System.out.println("3-1. reduce 초기값 X - 요소들을 하나의 값으로 누적");
        Optional<Integer> reduce1 = numbers.stream()
                .reduce((a, b) -> a + b); // 여기서는 누적합
        System.out.println("reduce1 = " + reduce1.orElseThrow());


        System.out.println("3-2. reduce 초기값 O - 요소들을 하나의 값으로 누적");
        Integer reduce2 = numbers.stream()
                .reduce(100, (a, b) -> a + b); // 여기서는 누적합
        System.out.println("reduce2 = " + reduce2);


        // min
        System.out.println("4. min - 최솟값을 반환");
        Optional<Integer> min = numbers.stream()
                .min(Integer::compareTo);
        System.out.println("min = " + min.orElseThrow());


        // max
        System.out.println("4. max - 최댓값을 반환");
        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);
        System.out.println("max = " + max.orElseThrow());
    }
}
