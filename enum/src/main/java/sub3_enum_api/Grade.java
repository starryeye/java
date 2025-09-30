package sub3_enum_api;

public enum Grade {

    SILVER,
    GOLD,
    PLATINUM,
    DIAMOND,
    ;

    /**
     * enum 은 기본적으로 java.lang.Enum 을 묵시적 상속하여 아래와 같은 api 를 제공한다. (toString 을 제외하고 모든 메서드는 오버라이딩 불가능)
     *      name()
     *          enum 에 선언되어있는 상수의 이름을 문자열로 반환
     *      valueOf(String)
     *          String 을 enum 상수 이름과 매핑하여 enum 상수의 instance 로 반환
     *      values()
     *          모든 enum 상수 instance 를 반환한다.
     *      toString()
     *          기본 구현은 enum 상수의 이름을 문자열로 반환하는 name() 과 동일하며, 원한다면 개발자가 커스텀 오버라이딩 가능
     *      ordinal()
     *          enum 상수의 선언 순서(0 index)를 반환한다.
     */
}
