package lambda.sub2_lambda_concept.sub3_practice.filter;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    public static List<Integer> filter(List<Integer> list, MyPredicate predicate) {
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
        System.out.println("origin array = " + numbers);

        // 음수만 뽑기
        List<Integer> negativeNumbers = filter(numbers, i -> i < 0);
        System.out.println("negative filtered = " + negativeNumbers);

        // 양수만 뽑기
        List<Integer> positiveNumbers = filter(numbers, i -> i > 0);
        System.out.println("positive filtered = " + positiveNumbers);
    }
}
