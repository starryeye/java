package array.time_complexity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Assignment {

    /**
     * 배열에서 index 를 통한..
     * 값 할당(변경), 값 접근의 경우 한번의 주소 계산으로 위치를 찾을 수 있다. O(1)
     */

    @DisplayName("배열의 index 를 통한 값 할당(변경)은 O(1) 이다.")
    @Test
    void test1() {

        int[] intArr = new int[3];

        intArr[0] = 1; // index-based assignment / modify value at index i
    }
}
