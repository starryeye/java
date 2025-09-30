package sub2_enum;

public class Main {

    public static void main(String[] args) {

        // enum 의 각 상수는 클래스이며 enum 선언 타입인 Grade 이다.
        System.out.println("class SILVER = " + Grade.SILVER.getClass());
        System.out.println("class GOLD = " + Grade.GOLD.getClass());

        // enum 의 각 상수는 서로 다른 인스턴스로 생성되어있다.
        // 참고
        // System.identityHashCode() : Java 가 관리하는 객체의 참조값을 숫자로 반환
        // Integer.toHexString() : 숫자를 16 진수 문자열로 변환
        System.out.println("SILVER instance address = " + Integer.toHexString(System.identityHashCode(Grade.SILVER)));
        System.out.println("GOLD instance address = " + Integer.toHexString(System.identityHashCode(Grade.GOLD)));


        // enum 은 switch 문에 사용할 수 있다.
        Grade grade = Grade.GOLD;
        int result = switch (grade) {
            case SILVER -> 1;
            case GOLD -> 2;
            default -> 0;
        };
    }
}
