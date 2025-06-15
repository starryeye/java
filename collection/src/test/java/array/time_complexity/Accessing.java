package array.time_complexity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Accessing {

    /**
     * 배열에서 index 를 통한..
     * 값 할당(변경), 값 접근의 경우 한번의 주소 계산으로 위치를 찾을 수 있다. O(1)
     */

    @DisplayName("배열의 index 를 통한 값 접근은 O(1) 이다.") // 접근을 조회라고 부르기도 하는데 검색과 헷갈릴 수 있어 조회 용어는 사용하지 않음..
    @Test
    void test1() {
        int[] intArr = {1, 2, 3, 4, 5};

        int result = intArr[0]; // Accessing an array element by index
    }
}
