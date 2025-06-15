package array.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayDefaultInitialization {

    @DisplayName("배열의 기본 초기 값은 0 이다.")
    @Test
    void test1() {
        int[] intArr = new int[2];

        assertThat(intArr).containsExactly(0, 0);
    }

    @DisplayName("참조형 배열의 기본 초기 값은 null 이다.")
    @Test
    void test2() {
        String[] strArr = new String[2];

        assertThat(strArr).containsExactly(null, null);
    }
}
