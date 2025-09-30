package sub3_enum_api.api;

import sub3_enum_api.Grade;

public class Name {

    public static void main(String[] args) {


        // enum 상수의 이름을 문자열로 반환한다. final 메서드로 오버라이딩 불가능
        System.out.println("Grade.GOLD.name() = " + Grade.GOLD.name());
    }
}
