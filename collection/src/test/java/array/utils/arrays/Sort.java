package array.utils.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class Sort {

    @DisplayName("Arrays.sort() 는 파라미터로 전달된 배열을 정렬한다. 정렬은 element 가 무엇인가에 크게 좌우된다.")
    @Test
    void test1() {

        int[] array = new int[]{5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(array));

        Arrays.sort(array); // 정렬한다.
        System.out.println(Arrays.toString(array));

        assertThat(array).containsExactly(1, 2, 3, 4, 5);
    }

    @DisplayName("Comparator 를 파라미터로 넘기지 않으면 primitive type 의 경우는 오름 차순 정렬된다.")
    @Test
    void test2() {

        int[] array = new int[]{5, 4, 3, 2, 1}; // primitive type element 를 가진 배열
        System.out.println(Arrays.toString(array));

        Arrays.sort(array); // Comparator 전달하지 않음
        System.out.println(Arrays.toString(array));

        assertThat(array).containsExactly(1, 2, 3, 4, 5);
    }

    @DisplayName("element 가 reference type 인 경우 Comparable 구현에 따라 정렬된다.")
    @Test
    void test3() {

        Integer[] array = new Integer[]{5, 4, 3, 2, 1}; // reference type element 를 가진 배열
        System.out.println(Arrays.toString(array));

        Arrays.sort(array); // Comparator 전달하지 않음, Integer 는 Comparable 을 구현한 객체이다.
        System.out.println(Arrays.toString(array));

        assertThat(array).containsExactly(1, 2, 3, 4, 5);
    }

    @DisplayName("element 가 reference type 이면, Comparator 를 이용하여 정렬할 수 있다. primitive type 은 타입 파라미터에 사용될 수 없어서 불가능(boxed 해서 사용하자.)")
    @Test
    void test4() {

        Integer[] array = new Integer[]{5, 4, 3, 2, 1}; // reference type element 를 가진 배열
        System.out.println(Arrays.toString(array));

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? -1 : o1 > o2 ? 1 : 0;
            }
        }); // Comparator 를 익명 클래스로 전달
        System.out.println(Arrays.toString(array));

        assertThat(array).containsExactly(1, 2, 3, 4, 5);
    }
}
