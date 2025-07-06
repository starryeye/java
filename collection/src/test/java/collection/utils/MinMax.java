package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MinMax {

    /**
     * 참고
     * 최소/최대 값을 찾기에 최적화된 자료구조의 경우라도 O(n) 이 걸리므로 주의해야한다. (TreeSet, TreeMap, PriorityQueue 등)
     */

    @DisplayName("Collections.min() 은 전달된 Collection 에 담긴 element 들을 순회하여, 가장 작은 element 을 반환한다. 시간 복잡도 : O(n)")
    @Test
    void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(3);

        Integer min = Collections.min(list); // Comparator 를 추가로 전달 할 수 있다.
        assertThat(min).isEqualTo(1);
    }

    @DisplayName("Collections.max() 는 전달된 Collection 에 담긴 element 들을 순회하여, 가장 큰 element 을 반환한다. 시간 복잡도 : O(n)")
    @Test
    void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(3);

        Integer max = Collections.max(list); // Comparator 를 추가로 전달 할 수 있다.
        assertThat(max).isEqualTo(5);
    }
}
