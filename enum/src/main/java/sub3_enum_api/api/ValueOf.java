package sub3_enum_api.api;

import sub3_enum_api.Grade;

public class ValueOf {

    public static void main(String[] args) {


        String gold = "GOLD";

        // String 을 enum 상수 이름과 매핑하여 enum 인스턴스로 반환
        Grade grade = Grade.valueOf(gold);


        System.out.println("grade = " + grade.toString());
    }
}
