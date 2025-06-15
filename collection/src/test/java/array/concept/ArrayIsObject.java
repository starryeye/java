package array.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayIsObject {

    @DisplayName("배열은 Object 이다.")
    @Test
    void test1() {

        int[] arr = {1, 2, 3};

        assertThat(arr).isInstanceOf(Object.class);
    }
}
