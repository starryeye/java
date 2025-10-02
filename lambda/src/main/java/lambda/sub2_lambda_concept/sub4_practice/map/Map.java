package lambda.sub2_lambda_concept.sub4_practice.map;

import java.util.ArrayList;
import java.util.List;

public class Map {

    // 고차 함수, 함수를 매개변수로 받는 함수
    private static List<String> map(List<String> list, MyStringFunction myStringFunction) {

        List<String> result = new ArrayList<>();
        for (String word : list) {
            String applied = myStringFunction.apply(word);
            result.add(applied);
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> words = List.of("hello", "lambda", "world");
        System.out.println("origin list = " + words);

        // 대문자 변환
        List<String> result1 = map(words, word -> word.toUpperCase());
        System.out.println("result1 = " + result1);

        // 문자열 앞뒤에 "***" 붙이기
        List<String> result2 = map(words, word -> "***" + word + "***");
        System.out.println("result2 = " + result2);
    }
}
