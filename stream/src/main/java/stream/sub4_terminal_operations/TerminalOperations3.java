package stream.sub4_terminal_operations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalOperations3 {

    /**
     * 최종 연산자에 대해 알아본다.
     *
     * 1. collect
     *      <R, A> R collect(Collector<? super T, A, R> collector);
     *      Collectors 를 사용하여 다양한 형태로 결과를 수집할 수 있다.
     *      sub6 에서 더 자세하게 다룬다.
     * 2. toList
     *      collect(Collectors.toList()) 와 동의어이다.
     *      default List<T> toList()
     * 3. toArray
     *      <A> A[] toArray(IntFunction<A[]> generator);
     *      스트림을 배열로 변환한다.
     *      대충 보면 IntFunction 이라 뭔가 숫자만 될것 같지만.. 모든 reference type 을 지원한다.
     */

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10);

        // collect
        System.out.println("1. collect - List 로 요소들을 수집");
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0) // 여기선 짝수 필터링
                .collect(Collectors.toList());
        System.out.println("even numbers: " + evenNumbers);


        // toList
        System.out.println("2. toList - List 로 요소들을 수집");
        List<Integer> oddNumbers = numbers.stream()
                .filter(n -> n % 2 != 0) // 여기선 홀수 필터링
                .toList();
        System.out.println("odd numbers: " + oddNumbers);


        // toArray
        System.out.println("3. toArray - 배열로 요소들을 수집");
        Integer[] array1 = numbers.stream()
                .filter(n -> n % 3 == 0) // 여기선 3의 배수 필터링
                .toArray(Integer[]::new);
        System.out.println("array: " + Arrays.toString(array1));

        System.out.println("3. toArray - String test");
        String[] array2 = List.of("AA", "BB", "CC")
                .toArray(String[]::new);
        System.out.println("array: " + Arrays.toString(array2));
    }
}
