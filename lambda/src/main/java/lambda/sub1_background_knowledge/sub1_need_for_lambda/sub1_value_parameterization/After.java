package lambda.sub1_background_knowledge.sub1_need_for_lambda.sub1_value_parameterization;

public class After {

    /**
     * Before.java 와 After.java 를 비교해보면 변하는 부분에 해당하는 "Hello Java", "Hello Spring" 을 파라미터로 추출하였다.
     *
     * Value Parameterization (값 매개변수화)
     *      구체적인 값(문자열, 숫자 등)을 메서드 내에 두지 않고 매개변수를 통해
     *      외부에서 전달 받도록 하여, 매개변수에 따라 메서드의 동작을 달리하도록 한다. (재사용성 up)
     */

    public static void main(String[] args) {
        hello("Java");      // 동작을 결정하는 값을 전달
        hello("Spring");    // 동작을 결정하는 값을 전달
    }

    private static void hello(String str) { // 변하는 부분을 value parameterization 을 적용하였다.
        System.out.println("start");        // 변하지 않는 부분
        System.out.println("Hello " + str); // Before.java 와 비교하여 변하지 않는 부분으로 변경됨
        System.out.println("end");          // 변하지 않는 부분
    }


}
