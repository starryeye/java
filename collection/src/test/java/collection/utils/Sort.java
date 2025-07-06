package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Sort {

    /**
     * Collections.sort() 를 사용하지 말고,
     * SRP 관점에서 각 자료구조에 sort() 메서드가 있는 것이 자연스럽고
     * 자기자신의 element 를 정렬한다는 최적화 관점도 있으므로 각 자료구조가 제공하는 sort() 메서드를 이용하도록 하자.
     */

    @DisplayName("Collections.sort() 는 전달된 Collection 을 정렬해준다.")
    @Test
    void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println("original = " + list);

        Collections.sort(list); // Comparator 도 전달 가능하다. Comparator 없으면 natural ordering sort
        System.out.println("sorted = " + list);
        assertThat(list).containsExactly(1, 2, 3, 4, 5);
    }

    @DisplayName("Collections.reverse() 는 전달된 Collection 을 정렬해준다.")
    @Test
    void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("original = " + list);

        Collections.reverse(list); // Comparator 전달 불가능, natural ordering 의 역으로 정렬 해준다.
        System.out.println("sorted = " + list);
        assertThat(list).containsExactly(5, 4, 3, 2, 1);
    }
}
