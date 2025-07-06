package collection.arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListGet {

    @Test
    @DisplayName("get(index)로 요소를 조회할 수 있다")
    void test1() {

        ArrayList<String> list = new ArrayList<>();

        list.add("Hello");

        assertThat(list.get(0)).isEqualTo("Hello");
    }

    @Test
    @DisplayName("getFirst()로 첫번째 요소를 조회할 수 있다")
    void test2() {

        ArrayList<String> list = new ArrayList<>();

        list.add("Hello");
        list.add("World");

        assertThat(list.getFirst()).isEqualTo("Hello");
    }

    @Test
    @DisplayName("getLast()로 마지막 요소를 조회할 수 있다")
    void test3() {

        ArrayList<String> list = new ArrayList<>();

        list.add("Hello");
        list.add("World");

        assertThat(list.getLast()).isEqualTo("World");
    }


}
