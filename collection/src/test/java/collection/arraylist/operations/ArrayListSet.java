package collection.arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListSet {

    @Test
    @DisplayName("set(index, value)로 요소를 변경할 수 있다")
    void updateElement() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.set(0, "Z");

        assertThat(list).containsExactly("Z");
    }
}
