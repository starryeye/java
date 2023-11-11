package dev.practice.java8.subject4_Optional.b_Optional사용;

import java.util.List;

public class App {

    /**
     * Optional..
     * Java 8 에 추가된 인터페이스이다.
     * 값 하나만을 담고 있을 수도.. 비어있을 수도.. 있다.
     */

    /**
     * 자바 프로그래밍에서 NullPointerException을 종종 보게 되는 이유
     * - null을 리턴하거나.. null 체크를 깜빡하거나..
     *
     * 메소드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법
     * - 예외를 던진다. (비싸다, 스택트레이스를 찍어두니까.)
     * - null을 리턴한다. (비용 문제가 없지만 그 코드를 사용하는 클라이언트 코드가 주의해야한다.)
     * - (자바 8부터) Optional 을 리턴한다. (클라이언트에 코드에게 명시적으로 빈 값일 수도 있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다.) Optional
     * - 값 한 개가 들어있을 수 도 없을 수 도 있는 컨네이너.
     *
     * 주의사항
     * - 리턴값으로만 쓰기를 권장 한다. (메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자.)
     * -> 메서드 파라미터로 Optional 을 사용하는 것은 Lecture.java로 가보자..
     * -> 맵의 정의로 key 타입은 null 이면 안된다 이다. key 를 optional 로 사용해서 빈 값일 수 있다는 것은 정의를 깨트리는 것이다.
     *
     * - Optional 을 리턴하는 메소드에서 null 을 리턴하지 말자.
     * -> 메서드 파라미터로 Optional 을 사용하는 것과 비슷한 이유이다. 필요하다면 Optional.empty() 를 리턴하자.
     *
     * - primitive 타입용 Optional 을 사용하자. OptionalInt, OptionalLong,...
     * -> Optional.of(10); 처럼 primitive 을 Optional 에다가 넣을 순 있지만, 성능상 좋지 않다. 내부에서 boxing, unboxing 처리가 일어남..
     * -> OptionalInt.of(10); 를 사용하자.
     *
     * - Collection, Map, Stream Array, Optional 처럼 컨테이너 성격의 인스턴스는.. Opiontal 로 감싸지 말 것.
     * -> 컨테이너 자체가 이미 비어있다는 것을 표현할 수 있다... 의미가 없음
     *
     *
     * 참고
     * - https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
     * - https://www.oracle.com/technical-resources/articles/java/java8-optional.html
     * - 이팩티브 자바 3판, 아이템 55 적절한 경우 Optional을 리턴하라.
     */

    public static void main(String[] args) {

        Lecture java = new Lecture(1, "Java", true);
        Lecture spring = new Lecture(2, "Spring", true);
        Lecture jpa = new Lecture(3, "JPA", false);
        Lecture redis = new Lecture(4, "Redis", false);
        Lecture kafka = new Lecture(5, "Kafka", false);
        Lecture springBoot = new Lecture(6, "Spring Boot", false);
        List<Lecture> backend = List.of(java, spring, jpa, redis, kafka, springBoot);


    }
}
