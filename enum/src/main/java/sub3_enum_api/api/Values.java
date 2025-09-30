package sub3_enum_api.api;

import sub3_enum_api.Grade;

import java.util.Arrays;

public class Values {

    public static void main(String[] args) {

        // 모든 enum 상수 instance 를 반환한다.
        Grade[] values = Grade.values();
        System.out.println("Grade.values() = " + Arrays.toString(values));
    }
}
