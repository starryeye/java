package sub3_enum_api.api;

import sub3_enum_api.Grade;

public class ToString {

    public static void main(String[] args) {

        // 기본 구현은 enum 상수의 이름을 문자열로 반환하는 name() 과 동일하며, 원한다면 개발자가 커스텀 오버라이딩 가능
        System.out.println("Grade.GOLD.toString() = " + Grade.GOLD.toString());
    }
}
