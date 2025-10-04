package lambda.sub2_lambda_concept.sub1_lambda;

public class Lambda {

    /**
     * Lambda ..
     *      Java 8 부터 도입되었다.
     *      Java 에서 함수형 프로그래밍을 지원하기 위한 핵심 기능이다.
     *      Lambda 는 익명 함수이다.
     *          익명 클래스, 익명 메서드가 아니라 "익명 함수"이다.
     *          이름 없는 하나의 코드 영역
     *      Lambda 는 new 키워드를 사용하지 않지만... 익명 클래스 처럼 클래스 메타데이터가 만들어지고, 인스턴스도 만들어진다.
     *          즉, 람다도 인스턴스이고 Java 는 메서드 파라미터 인수로 인스턴스 참조 값을 사용할 수 있으므로
     *          메서드에 전달(반환도 가능)하다.
     *          인스턴스이므로 참조형 변수에 대입도 가능하다.
     *      의의
     *          익명클래스를 사용할때와 비교하여 Boilerplate 코드를 줄이고 생산성과 가독성을 증가시킨다.
     *
     * 람다식
     *      람다는 익명 함수를 지칭하는 개념이다.
     *      람다식은 람다를 구현하는 구체적인 문법 표현이다.
     */

    public static void main(String[] args) {


        // 익명 클래스
        Procedure anonymousClass = new Procedure() {
            @Override
            public void execute() {
                System.out.println("Hello Lambda");
            }
        };
        System.out.println("anonymousClass.getClass(): " + anonymousClass.getClass());
        System.out.println("anonymousClass instance : " + anonymousClass);



        // 람다도 클래스 메타데이터가 만들어지고, 인스턴스도 만들어진다.
        Procedure lambda = () -> System.out.println("Hello Lambda");
        System.out.println("lambda.getClass(): " + lambda.getClass());
        System.out.println("lambda instance : " + lambda);


        // 참고 현재 클래스(Lambda)의 메타데이터
        System.out.println("Lambda.class : " + Lambda.class); // 익명 클래스와 분명히 구분됨(다름)을 알 수 있음
    }
}
