package arraylist.declaration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListDefaultValue {

    @Test
    @DisplayName("ArrayList는 add() 하기 전까지 null 이나 0 등의 기본 값이 존재하지 않는다")
    void test1() {
        ArrayList<Integer> list = new ArrayList<>(10); // 내부 용량은 10개 확보됨

        assertThat(list.size()).isEqualTo(0); // 실제 크기는 0
        assertThat(list).isEmpty();
        assertThatThrownBy(() -> list.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("add()로 값을 삽입해야만 내부에 값이 존재하게 된다")
    void addCreatesValue() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Hello");

        assertThat(list.getFirst()).isEqualTo("Hello");
    }
}
