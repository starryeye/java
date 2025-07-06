package collection.arraylist.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListInternalCopyBehavior {

    @Test
    @DisplayName("ArrayList 는 내부적으로 정적 배열([])을 사용하고 기존 capacity 를 초과하게 되면 새로운 배열로 복사되며 동적배열을 지원한다.")
    void test1() {

        ArrayList<Integer> list = new ArrayList<>(1); // capacity(1)
        list.add(1);
        list.add(2); // size(1), capacity(1) 인 상태에서 원소가 하나 늘어버리면, 내부 배열의 bound 를 벗어남..
        // grow() 메서드를 통해.. size(2) 를 커버하는 capacity 를 가진 새로운 배열로 얕은 복사한다. (Arrays.copyOf 이용)
        // 기존의 배열은 GC 에 의해 자연스럽게 삭제됨

        assertThat(list).hasSize(2).containsExactly(1, 2);
    }
}
