package arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListContain {

    @Test
    @DisplayName("contains(value)로 값 포함 여부를 확인할 수 있다")
    void containsValue() {
        ArrayList<String> list = new ArrayList<>();
        list.add("X");

        assertThat(list.contains("X")).isTrue();
        assertThat(list.contains("Y")).isFalse();
    }
}
