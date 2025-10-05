package lambda.sub6_stream.sub2_declarative_programming;

public interface Description {

    /**
     * 명령형 프로그래밍과 선언적 프로그래밍에 대해 알아본다.
     *
     * 명령형 프로그래밍 (Imperative programming)
     *      "어떻게"(How) 수행되는지.. 수행 절차를 명시하는 방식이다.
     *      낮은 추상화가 특징이다.
     *          수행 절차가 구체적이다.
     *          상태 변화가 보인다. (변수 값이 어떻게 변화하는지 눈에 보임)
     *          개발자가 수행 절차 및 상태 변화를 상세히 제어 가능
     *          코드가 한눈에 안들어올 가능성이 높음
     *
     * 선언적 프로그래밍 (Declarative programming)
     *      "무엇"(What)을 수행하는지.. 원하는 결과를 명시하는 방식
     *      높은 추상화가 특징이다.
     *          수행 절차 및 상태 변화는 내부 구현으로 숨기고 원하는 결과와 핵심 로직만 보인다.
     *          코드가 한눈에 들어온다.
     *      람다와 같은 도구를 사용하면 선언적 스타일에 가깝다.
     *
     * 예시..
     *      lambda.sub6_stream.sub1_stream_v0.ExampleUsage2 참고하자.
     *          private static List<String> lambda(List<User> users)
     *              선언적 프로그래밍 방식이다.
     *              물론 GenericListFilter.filter() 메서드 내부로 들어가면 구체화 되지만 여기서 말하는 건 해당 메서드만 보자면.. 이다.
     *          private static List<String> direct(List<User> users)
     *              명령형 프로그래밍 방식이다.
     */
}
