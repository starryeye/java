package lambda.sub2_lambda_concept.sub4_practice.filter;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    // 고차 함수, 함수를 매개변수로 받는 함수
    private static List<Integer> filter(List<Integer> list, MyIntPredicate predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer value : list) {
            if (predicate.test(value)) {
                result.add(value);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> numbers = List.of(-3, -2, -1, 1, 2, 3, 5);
        System.out.println("origin list = " + numbers);

        // 음수만 뽑기
        List<Integer> negativeNumbers = filter(numbers, value -> value < 0);
        System.out.println("negative filtered = " + negativeNumbers);

        // 짝수만 뽑기
        List<Integer> positiveNumbers = filter(numbers, value -> value % 2 == 0);
        System.out.println("positive filtered = " + positiveNumbers);
    }
}
