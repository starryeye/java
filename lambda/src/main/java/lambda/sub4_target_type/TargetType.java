package lambda.sub4_target_type;

import lambda.sub2_lambda_concept.sub4_practice.build.MyStringFunction;

public class TargetType {

    /**
     * 람다는 그 자체만으로는 타입이 없으며, "타겟 타입"이라고 불리는 대입되는 참조형에 의해 타입이 결정된다.
     */

    public static void main(String[] args) {

        // functionA 에 대입될 때 람다(i -> "my value is " + i) 의 타입이 FunctionA 로 결정
        FunctionA functionA = i -> "my value is " + i;

        // functionB 에 대입될 때 람다(i -> "my value is " + i) 의 타입이 FunctionB 로 결정
        FunctionB functionB = i -> "my value is " + i;

        // 람다는 한번 할당이 된 이후에는 타입이 결정되어 버리므로..
        // 동일한 메서드 시그니처와 반환타입을 가지는 서로다른 함수형 인터페이스에 대입연산이 불가능하다.
//        functionA = functionB; // compile error..
    }

    @FunctionalInterface
    private interface FunctionA {
        String apply(Integer value);
    }

    @FunctionalInterface
    private interface FunctionB {
        String apply(Integer value);
    }
}
