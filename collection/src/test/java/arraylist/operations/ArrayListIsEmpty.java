package arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListIsEmpty {

    @Test
    @DisplayName("isEmpty()는 size()가 0일 때 true를 반환한다")
    void isEmptyBasedOnSize() {
        ArrayList<String> list = new ArrayList<>(10);

        assertThat(list.isEmpty()).isTrue();
        assertThat(list.size()).isEqualTo(0);

        list.add("A");

        assertThat(list.isEmpty()).isFalse();
        assertThat(list.size()).isEqualTo(1);
    }
}
