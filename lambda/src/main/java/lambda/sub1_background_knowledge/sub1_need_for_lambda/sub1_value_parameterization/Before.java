package lambda.sub1_background_knowledge.sub1_need_for_lambda.sub1_value_parameterization;

public class Before {

    /**
     * helloJava(), helloSpring() 메서드에 존재하는
     * 공통의 중복 코드를 제거해야한다...
     * -> 해결 : After.java
     */

    public static void main(String[] args) {
        helloJava();
        helloSpring();
    }

    private static void helloJava() {
        System.out.println("start");            // 변하지 않는 부분
        System.out.println("Hello Java");       // 변하는 부분
        System.out.println("end");              // 변하지 않는 부분
    }

    private static void helloSpring() {
        System.out.println("start");            // 변하지 않는 부분
        System.out.println("Hello Spring");     // 변하는 부분
        System.out.println("end");              // 변하지 않는 부분
    }
}
