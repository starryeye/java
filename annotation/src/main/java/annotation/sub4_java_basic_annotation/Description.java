package annotation.sub4_java_basic_annotation;

public interface Description {

    /**
     * 자바가 기본적으로 제공해주는 대표적 annotation 에 대해 알아본다.
     *
     * @Override
     *      메서드가 부모 클래스(또는 인터페이스)의 메서드를 오버라이딩하고 있음을 명시
     *      컴파일러에게 해당 메서드가 상위 클래스나 인터페이스의 메서드를 재정의하고 있음을 알린다.
     *      메서드 시그니처가 정확히 일치하지 않으면 컴파일 에러를 발생시켜 실수를 방지한다.
     *      meta annotation
     *          @Retention(RetentionPolicy.SOURCE)
     *              컴파일 시점에 검사하기 위해 사용됨
     *
     * @FunctionalInterface
     *      인터페이스가 함수형 인터페이스임을 명시.
     *      컴파일러가 인터페이스에 추상 메서드가 하나뿐인지 확인하고, 두 개 이상의 추상 메서드가 있으면 에러를 발생시킴
     *      meta annotation
     *          @Retention(RetentionPolicy.RUNTIME)
     *              컴파일 시점에 검사 뿐만아니라..
     *              런타임에 프레임워크나 도구가 함수형 인터페이스임을 확인하고 동적으로 람다 기반 최적화를 수행하기 위함이라 한다.
     *
     * @Deprecated
     *      클래스, 메서드, 필드 등이 더 이상 사용되지 않으며, 향후 제거될 가능성이 있음을 알림.
     *
     * @SuppressWarnings
     *      컴파일러가 특정 경고를 무시하도록 지시.
     */
}
