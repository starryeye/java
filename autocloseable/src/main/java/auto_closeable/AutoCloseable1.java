package auto_closeable;

public interface AutoCloseable1 {

    /**
     * AutoCloseable 에 대해 다루어본다.
     *
     * AutoCloseable 의 의의에 대해 알아보기 위해 하나의 예제를 점진적으로 발전 시키며 설명함.
     *      common 패키지는 모든 버전의 공통적으로 사용됨.
     *      v1 ~ v4 패키지는 발전되는 양상을 보여줌
     *      Service 객체가 Resource 라는 외부 자원을 사용한다고 가정
     *
     * 핵심 이점은 v4 참고
     *
     * 참고
     * Java 객체는 GC 가 되지만, Java 외부의 자원은 자동으로 GC 가 되지 않는다.
     * 따라서, 무조건 명시적으로 정리를 해줘야한다.
     *
     *
     * 팁
     *      1. try with resources 를 사용했는데.. finally 구문을 사용한다면, 자원정리가 모두 된 후, finally 구문이 수행될 것이다.
     *      2. try with resources 에서 try 구문에 선언된 순서의 반대로 자원정리 순서가 결정된다.
     */
}
