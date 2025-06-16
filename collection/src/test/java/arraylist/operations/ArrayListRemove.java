package arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListRemove {

    @Test
    @DisplayName("remove(index)로 index 기반으로 요소를 제거할 수 있다")
    void test1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        list.remove(0); // "A" 제거
        assertThat(list).containsExactly("B");
    }

    @Test
    @DisplayName("removeFirst()로 첫번째 요소를 제거할 수 있다")
    void test2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        list.removeFirst(); // "A" 제거
        assertThat(list).containsExactly("B");
    }

    @Test
    @DisplayName("removeLast()로 마지막 요소를 제거할 수 있다")
    void test3() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        list.removeLast(); // "B" 제거
        assertThat(list).containsExactly("A");
    }

    @Test
    @DisplayName("remove(Object)로 특정 값을 제거할 수 있다")
    void test4() {
        ArrayList<String> list = new ArrayList<>();
        list.add("X");
        list.add("Y");

        list.remove("X");
        assertThat(list).containsExactly("Y");
    }

    @Test
    @DisplayName("removeAll()로 특정 값들을 제거할 수 있다")
    void test5() {
        ArrayList<String> list = new ArrayList<>();
        list.add("X");
        list.add("Y");
        list.add("Z");

        list.removeAll(List.of("X", "Z"));
        assertThat(list).containsExactly("Y");
    }
}
