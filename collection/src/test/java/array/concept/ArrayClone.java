package array.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayClone {


    @DisplayName("배열의 clone() 메서드는 얕은 복사이다.")
    @Test
    void test1() {

        int[] original = {1, 2, 3};
        int[] cloned = original.clone();

        assertThat(original == cloned).isFalse(); // 동일성 비교
        assertThat(original).containsExactly(cloned); // 동등성 비교, 순서를 포함하여 값을 비교한다. (isEqualTo() 는 값 비교만 한다.)
    }

    static class Item {
        final String name;

        Item(String name) {
            this.name = name;
        }
    }

    @DisplayName("참조형 배열에서 clone() 메서드는 얕은 복사이므로 내부 원소의 참조는 동일하다.")
    @Test
    void test2() {

        Item[] original = {
                new Item("a"),
                new Item("b")
        };
        Item[] cloned = original.clone();

        // 배열 객체 자체는 다르다 (동일성 false)
        assertThat(original).isNotSameAs(cloned);

        // 배열의 길이와 요소의 순서/값은 같다 (동등성 true)
        assertThat(cloned).containsExactly(original);

        // 내부 요소는 같은 객체를 참조해야 한다 (얕은 복사이므로)
        for (int i = 0; i < original.length; i++) {
            assertThat(cloned[i]).isSameAs(original[i]); // 동일성 검사
        }
    }
}
