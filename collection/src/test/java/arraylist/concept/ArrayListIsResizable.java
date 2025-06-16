package arraylist.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListIsResizable {

    @Test
    @DisplayName("ArrayList 는 add() 를 통해 원소를 넣으면, 크기가 동적으로 늘어난다.")
    void test1() {
        ArrayList<Integer> list = new ArrayList<>(); // 초기 용량 10 (내부 DEFAULT_CAPACITY)

        assertThat(list.size()).isEqualTo(0); // 원소 갯수 0

        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }

        assertThat(list).hasSize(100).contains(1, 50, 100); // 초기 용량이 10 이었지만, 100 개를 담을 수 있었다. (capacity 가 동적으로 늘어남)
    }
}
