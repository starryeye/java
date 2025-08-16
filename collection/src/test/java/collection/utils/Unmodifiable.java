package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Unmodifiable {

    /**
     * List.copyOf() 도 볼 것..
     */

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

    @DisplayName("Collections.unmodifiableXXX() 는 얕은 복사로 수행된다. 1")
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

    @DisplayName("Collections.unmodifiableXXX() 는 얕은 복사로 수행된다. 2")
    @Test
    void test3() {

        /**
         * test2 와 test3 은 결국 동일한 걸로 보이는데.. 중요한 차이점이 있다..
         * test2
         *      원본 list 를 그대로 감싸는 뷰(view)를 반환
         *      따라서 원본 list 에 요소를 추가/삭제하면 unmodifiableList 에서도 그대로 반영됨.
         * test3
         *      원본 list 를 복사한 새 ArrayList 를 감싸는 뷰를 반환
         *      따라서 원본 list 를 수정해도 unmodifiableList 에는 영향을 주지 않는다.
         *
         */

        List<Item> list = new ArrayList<>();
        list.add(new Item("AA"));
        list.add(new Item("BB"));
        list.add(new Item("CC"));

        List<Item> unmodifiableList = Collections.unmodifiableList(new ArrayList<>(list));

        assertThat(list == unmodifiableList).isFalse(); // Collection 끼리는 서로 다른 인스턴스이다.
        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(i) == unmodifiableList.get(i)).isTrue(); // element 는 동일하다.
        }
    }

    @DisplayName("Collections.unmodifiableXXX() 에 CUD 를 하면 예외 발생함")
    @Test
    void test4() {

        List<Item> list = new ArrayList<>();
        list.add(new Item("AA"));
        list.add(new Item("BB"));
        list.add(new Item("CC"));
        List<Item> unmodifiableList = Collections.unmodifiableList(list);

        assertThatThrownBy(() -> unmodifiableList.add(new Item("DD")))
                .isInstanceOf(UnsupportedOperationException.class);
    }


    static class Item {
        final String name;

        Item(String name) {
            this.name = name;
        }
    }
}
