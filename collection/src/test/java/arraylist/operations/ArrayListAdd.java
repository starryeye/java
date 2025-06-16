package arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListAdd {

    @Test
    @DisplayName("add(E)로 리스트 뒤에 요소를 추가할 수 있다")
    void addToEnd() {

        ArrayList<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");

        assertThat(list).containsExactly("A", "B");
    }

    @Test
    @DisplayName("add(index, E)로 특정 위치에 요소를 삽입할 수 있다")
    void insertAtIndex() {

        ArrayList<String> list = new ArrayList<>();

        list.add("A");
        list.add("C");
        list.add(1, "B"); // A, B, C

        assertThat(list).containsExactly("A", "B", "C");
    }
}
