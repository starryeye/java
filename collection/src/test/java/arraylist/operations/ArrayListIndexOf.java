package arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListIndexOf {

    @Test
    @DisplayName("indexOf(value)는 첫 번째 위치(index)를 반환하고, 없으면 -1을 반환한다")
    void indexOfElement() {

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");

        assertThat(list.indexOf("A")).isEqualTo(0);
        assertThat(list.indexOf("B")).isEqualTo(1);
        assertThat(list.indexOf("Z")).isEqualTo(-1);
    }

    @Test
    @DisplayName("lastIndexOf(value)는 마지막 위치를 반환한다")
    void lastIndexOfElement() {
        ArrayList<String> list = new ArrayList<>();
        list.add("X");
        list.add("Y");
        list.add("X");

        assertThat(list.lastIndexOf("X")).isEqualTo(2);
        assertThat(list.lastIndexOf("Y")).isEqualTo(1);
        assertThat(list.lastIndexOf("Z")).isEqualTo(-1);
    }
}
