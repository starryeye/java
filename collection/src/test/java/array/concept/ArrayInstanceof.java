package array.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayInstanceof {

    @DisplayName("배열은 Cloneable, Serializable 을 구현한다.")
    @Test
    void test1() {
        int[] arr = new int[3];
        assertThat(arr)
                .isInstanceOf(Cloneable.class)
                .isInstanceOf(java.io.Serializable.class);
    }
}
