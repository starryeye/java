package lambda.sub2_lambda_concept.sub4_practice.reduce;

import java.util.List;

public class Reduce {

    /**
     * 참고.
     * 보통 여러 값을 특정 로직에 넣어서 하나의 최종 값으로 반환하는 것을 reduce, fold 로 표현한다.
     */

    // 고차 함수, 함수를 매개변수로 받는 함수
    private static int reduce(List<Integer> list, int initial, MyIntBiFunction function) {

        for (int value : list) {
            initial = function.apply(initial, value);
        }

        return initial;
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4);
        System.out.println("given list = " + numbers);

        // 누적 합
        System.out.println("cumulative sum = " + reduce(numbers, 0, (a, b) -> a + b));

        // 누적 곱
        System.out.println("cumulative product = " + reduce(numbers, 1, (a, b) -> a * b));
    }
}
