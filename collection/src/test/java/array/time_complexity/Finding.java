package array.time_complexity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Finding {

    /**
     * 정렬되지 않은 배열에서 값이 있는지 검색하는 것은 O(n) 이다.
     */

    @DisplayName("배열의 값 검색은 O(n) 이다.")
    @Test
    void test1() {
        int[] arr = {1, 2, 3, 4, 5};

        for (int e : arr) {
            if (e == 3) {
                System.out.println(e);
            }
        }
    }
}
