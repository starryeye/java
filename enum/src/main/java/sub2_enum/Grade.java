package sub2_enum;

public enum Grade {

    /**
     * 등급 (SILVER, GOLD, PLATINUM, DIAMOND) 을 예시로 enum 을 알아본다.
     *
     * enum..
     *      enum 도 클래스이다.
     *      enum 은 자동으로 java.lang.Enum 을 묵시적 상속한다.
     *      enum 은 다른 클래스를 상속 받을 수 없다.
     *      enum 은 다른 인터페이스를 구현할 수 있다.
     *      enum 은 일반 클래스와 다르게 abstract 메서드를 선언할 수 있다.
     *              이 경우 상수에 익명 클래스 방식으로 해당 추상 메서드를 구현해줘야한다. (참고로 일반 클래스에 abstract 메서드를 선언하면 컴파일 에러이며, 클래스를 abstract 클래스로 변경해야함.)
     *      외부에서 new Grade() 느낌으로 내부의 새로운 등급을 만들어낼 수 없다.
     *      enum 에 선언된 각 상수는 해당 enum 클래스의 서로다른 인스턴스이다.
     *
     * 장점
     *      타입 안정성
     *          유효하지 않은 값이 입력될 가능성 없음, enum 이 클래스라서 해당 클래스의 인스턴스만 허용됨.
     *      간결성, 일관성
     *      확장성
     */

    SILVER,
    GOLD,
    PLATINUM,
    DIAMOND,
    ;

    /**
     * enum 타입은 아래의 코드와 거의 동일한 의미를 가진다.
     *
     * public class Grade extends Enum {
     *
     *     public static final Grade SILVER = new Grade();
     *     public static final Grade GOLD = new Grade();
     *     public static final Grade PLATINUM = new Grade();
     *     public static final Grade DIAMOND = new Grade();
     *
     *     private Grade() {}
     * }
     *
     * 참고.
     * 유틸성 클래스라 abstract 클래스로 만들어야하지만,
     * 내부에서 Grade 의 인스턴스 만들고 있는 형태라 abstract 키워드를 붙일 수 없으며
     * 원래 개발자는 Enum 을 상속한 클래스를 만들 수 없기도 하다.
     * enum 타입이 코드 상의 의미가 위와 거의 동일하다는 뜻으로 주석을 남김.
     */
}
