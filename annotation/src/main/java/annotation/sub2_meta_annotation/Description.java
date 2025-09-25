package annotation.sub2_meta_annotation;

public interface Description {

    /**
     * 메타 어노테이션에 대해 알아본다.
     *
     * 메타 어노테이션..
     *      annotation 을 선언하는데 사용되는 특별한 annotation 을 메타 어노테이션이라 부른다.
     *
     * @Retention
     *      annotation 의 생존 기간을 지정한다.
     *      종류
     *          RetentionPolicy.SOURCE
     *              소스코드에만 남아있고 컴파일 시점까지 살아있고 컴파일 시점 이후 제거된다.
     *          RetentionPolicy.CLASS
     *              컴파일 이후 .class 파일에 까지 살아있지만 런타임 시점에는 제거된다. (default)
     *          RetentionPolicy.RUNTIME
     *              런타임 시점에도 생존한다.
     *              reflection 과 annotation 을 결합하여 응용하려면 이 정책을 사용한다.
     *                  reflection 기술은 런타임에 동적으로 메타데이터를 뽑는 기술
     * @Target
     *      annotation 을 적용할 수 있는 위치를 한정한다.
     *      범위를 벗어나면, compile error
     *      종류
     *          java.lang.annotation.ElementType 참고
     *      참고.
     *      ElementType.TYPE 은 클래스, 인터페이스, enum 을 포함하는 것으로 우리가 일반적으로 XXX type 이라 부르는 그것이다.
     * @Documented
     *      Java API 문서 만들때 사용
     * @Inherited
     *      이 메타 어노테이션이 적용된 annotation 를 Z 라 하고
     *      Z 를 적용한 클래스를 A 라 하고
     *      A 를 상속한 클래스를 B 라 하겠다.
     *      B 도 A 와 같이 Z 가 적용된 클래스가 되는 것이다.
     *      참고.
     *          클래스간 상속에만 적용되며, interface 구현에는 적용되지 않는다.
     */
}
