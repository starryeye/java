package stream.sub4_terminal_operations;

import java.util.List;
import java.util.Optional;

public class TerminalOperations2 {

    /**
     * 최종 연산자에 대해 알아본다.
     *
     * 1. findFirst
     *      Optional<T> findFirst();
     *          첫번째 요소를 Optional 로 반환
     * 2. findAny
     *      Optional<T> findAny();
     *          아무 요소나 Optional 로 반환, multi thread 로 처리할 때 의미가 있다 (먼저 찾는게 반환됨)
     * 3. anyMatch
     *      boolean anyMatch(Predicate<? super T> predicate);
     *          요소들 중 하나라도 조건을 만족하는게 있으면 true
     * 4. allMatch
     *      boolean allMatch(Predicate<? super T> predicate);
     *          모든 요소들이 조건을 만족하면 true
     * 5. noneMatch
     *      boolean noneMatch(Predicate<? super T> predicate);
     *          모든 요소들이 조건을 만족하지않으면 true
     */

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10);

        // findFirst
        System.out.println("1. findFirst - 첫번째 요소 반환");
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5) // 여기선 5 초과되는 요소들 중..
                .findFirst();
        System.out.println("n > 5 first = " + first.orElseThrow());


        // findAny
        System.out.println("2. findAny - 아무 요소 하나 반환");
        Optional<Integer> any = numbers.stream()
                .filter(n -> n > 5) // 여기선 5 초과되는 요소들 중..
                .findAny();
        System.out.println("n > 5 any = " + any.orElseThrow());


        // anyMatch
        System.out.println("3. anyMatch - 조건을 만족하는 요소 존재 여부");
        boolean hasEven = numbers.stream()
                .anyMatch(n -> n % 2 == 0); // 짝수 요소가 있는지..
        System.out.println("hasEven = " + hasEven);


        // allMatch
        System.out.println("4. allMatch - 모든 요소가 특정 조건을 만족하는지");
        boolean allPositive = numbers.stream()
                .allMatch(n -> n > 0); // 양수인지..
        System.out.println("allPositive = " + allPositive);


        // noneMatch
        System.out.println("5. noneMatch - 조건을 만족하는 요소가 없는지");
        boolean noNegative = numbers.stream()
                .noneMatch(n -> n < 0);// 음수가 없는지
        System.out.println("noNegative = " + noNegative);
    }
}
