package method_reference.sub1_need_for_method_reference;

import java.util.function.BinaryOperator;

public class Example {

    /**
     * method reference 에 대해 알아본다.
     *
     * 람다가 단순히 어떤 메서드만 호출할 경우, 이를 축약해주는 문법이다.
     */

    public static void main(String[] args) {

        // 람다 식을 직접 씀 -> 해당 람다 식이 여러군데서 필요할 경우 동일한 "(a, b) -> a + b" 코드를 중복해서 입력
        // -> 유지보수성 떨어짐
        BinaryOperator<Integer> sum1 = (a, b) -> a + b;

        // 기존의 람다 식 중복 제거
        BinaryOperator<Integer> sum2 = (a, b) -> Example.add2(a, b);
        BinaryOperator<Integer> sum3 = Example.add3();


        // 메서드 레퍼런스 방법 -> 람다 식 없이 이미 정의된 메서드를 람다 식으로 변환해주는 문법적 편의 기능이다.
        BinaryOperator<Integer> sum4 = Example::add2;
    }

    private static Integer add2(Integer a, Integer b) {
        return a + b;
    }

    private static BinaryOperator<Integer> add3() {
        return (a, b) -> a + b;
    }
}
