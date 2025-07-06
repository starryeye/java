package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Unmodifiable {

    @DisplayName("Collections.unmodifiableXXX() 는 가변 Collection 을 이용하여 불변 Collection 으로 만들수 있다.")
    @Test
    void test1() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("class = " + list.getClass() + ", " + list);

        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println("class = " + unmodifiableList.getClass() + ", " + unmodifiableList);
    }
    // 참고, 불변 Collection 을 가변으로 만들려면, 가변 Collection 의 생성자에 불변 객체를 파라미터로 집어넣으면 만들어짐

    @DisplayName("Collections.unmodifiableXXX() 는 얕은 복사로 수행된다.")
    @Test
    void test2() {

        List<Item> list = new ArrayList<>();
        list.add(new Item("AA"));
        list.add(new Item("BB"));
        list.add(new Item("CC"));

        List<Item> unmodifiableList = Collections.unmodifiableList(list);

        assertThat(list == unmodifiableList).isFalse(); // Collection 끼리는 서로 다른 인스턴스이다.
        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(i) == unmodifiableList.get(i)).isTrue(); // element 는 동일하다.
        }

    }

    static class Item {
        final String name;

        Item(String name) {
            this.name = name;
        }
    }
}
