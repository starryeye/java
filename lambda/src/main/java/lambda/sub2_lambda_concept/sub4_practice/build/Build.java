package lambda.sub2_lambda_concept.sub4_practice.build;

public class Build {

    // 고차 함수, 함수를 반환하는 함수
    private static MyStringFunction buildGreeter(String greeting) {
        return name -> greeting + ", " + name;
    }

    public static void main(String[] args) {
        MyStringFunction greeter1 = buildGreeter("Hello");
        MyStringFunction greeter2 = buildGreeter("Hi");

        System.out.println(greeter1.apply("NickName1"));        // MyStringFunction 실행 -> Hello, NickName1
        System.out.println(greeter2.apply("NickName2"));        // MyStringFunction 실행 -> Hi, NickName2
    }
}
