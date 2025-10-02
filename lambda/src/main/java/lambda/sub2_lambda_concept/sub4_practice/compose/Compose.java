package lambda.sub2_lambda_concept.sub4_practice.compose;

public class Compose {

    // 두 개의 함수형 인터페이스를 순서대로 합성해주는 고차함수

    // 고차 함수, 함수를 매개변수로 받고 함수를 반환하는 함수
    private static MyStringFunction compose(MyStringFunction f1, MyStringFunction f2) {

        return str -> {
            String applied1 = f1.apply(str);
            String applied2 = f2.apply(applied1);
            return applied2;
        };
    }

    public static void main(String[] args) {
        MyStringFunction f1 = s -> s.toUpperCase();
        MyStringFunction f2 = s -> "***" + s + "***";

        MyStringFunction composed = compose(f1, f2);
        String result = composed.apply("Hello World");// 합성된 MyStringFunction 실행

        System.out.println("result = " + result);
    }
}
