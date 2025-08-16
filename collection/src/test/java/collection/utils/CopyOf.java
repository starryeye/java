package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyOf {

    @DisplayName("List.copyOf() 는 얕은 복사로 수행된다.")
    @Test
    void test1() {

        /**
         * List.copyOf(list); 는..
         * Collections.unmodifiableList(new ArrayList<>(list)); 와..
         * 기능적으로 거의 같다..
         *
         * List.copyOf 를 사용하자..
         */

        List<Unmodifiable.Item> list = new ArrayList<>();
        list.add(new Unmodifiable.Item("AA"));
        list.add(new Unmodifiable.Item("BB"));
        list.add(new Unmodifiable.Item("CC"));

        List<Unmodifiable.Item> copyOf = List.copyOf(list);

        assertThat(list == copyOf).isFalse(); // Collection 끼리는 서로 다른 인스턴스이다.
        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(i) == copyOf.get(i)).isTrue(); // element 는 동일하다.
        }
    }
}
