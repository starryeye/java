package array.utils.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyOf {

    static class Item {
        final String name;

        Item(String name) {
            this.name = name;
        }
    }

    @Test
    @DisplayName("Arrays.copyOf() 는 참조형 배열을 얕은 복사하며, 내부 참조는 동일하다.")
    void test1() {

        Item[] original = { new Item("a"), new Item("b") };
        Item[] copied = Arrays.copyOf(original, original.length);

        assertThat(copied).isNotSameAs(original);              // 동일성 비교, 배열 객체는 다름
        assertThat(copied).containsExactly(original);          // 동등성 비교, 값은 같음
        for (int i = 0; i < original.length; i++) {
            assertThat(copied[i]).isSameAs(original[i]);       // 내부 참조는 동일 (얕은 복사)
        }
    }

    @Test
    @DisplayName("Arrays.copyOf() 로 전달된 두번째 파라미터인 복사된 새로운 배열의 길이가 원본보다 작으면 앞부분만 복사된다.")
    void test2() {
        Item[] original = { new Item("x"), new Item("y"), new Item("z") };
        Item[] copied = Arrays.copyOf(original, 2);

        assertThat(copied.length).isEqualTo(2);
        assertThat(copied).containsExactly(original[0], original[1]);
    }

    @Test
    @DisplayName("Arrays.copyOf() 로 전달된 두번째 파라미터인 복사된 새로운 배열의 길이가 원본보다 크면 초과 부분은 null로 채워진다.")
    void test3() {
        Item[] original = { new Item("one"), new Item("two") };
        Item[] copied = Arrays.copyOf(original, 4);

        assertThat(copied.length).isEqualTo(4);
        assertThat(copied[0]).isSameAs(original[0]);
        assertThat(copied[1]).isSameAs(original[1]);
        assertThat(copied[2]).isNull();
        assertThat(copied[3]).isNull();
    }

    @Test
    @DisplayName("Arrays.copyOf() 는 null 을 포함한 배열도 정상적으로 복사한다.")
    void test4() {
        Item[] original = { new Item("a"), null, new Item("c") };
        Item[] copied = Arrays.copyOf(original, original.length);

        assertThat(copied.length).isEqualTo(original.length);
        assertThat(copied).containsExactly(original); // null 포함하여 동일
        assertThat(copied[0]).isSameAs(original[0]);
        assertThat(copied[1]).isNull();
        assertThat(copied[2]).isSameAs(original[2]);
    }
}
