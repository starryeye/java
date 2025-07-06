package array.utils.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AsList {

    /**
     * Arrays.asList() 를 쓰기 보다, List.of() 를 사용하도록 하자.
     */

    @DisplayName("Arrays.asList() 를 이용하면, 크기가 고정된 List 를 만들 수 있다. element 추가, 삭제 불가능")
    @Test
    void test1() {

        List<Integer> list1 = Arrays.asList(1, 2, 3); // 조회, 수정 가능 -> 수정이 가능해서 완전한 불변이 아니다..
        List<Integer> list2 = List.of(1, 2, 3); // 조회만 가능

        System.out.println("Class = " + list1.getClass() + ", " + list1);
        System.out.println("Class = " + list2.getClass() + ", " + list2);
    }

    @DisplayName("Arrays.asList() 를 이용하면, 배열을 List 로 만들 수 있다.")
    @Test
    void test2() {
        Integer[] array = {1, 2, 3};
        List<Integer> list = Arrays.asList(array); // 물론 element 추가 삭제는 불가능하다.

        System.out.println("Class = " + list.getClass() + ", " + list);
    }

    @DisplayName("Arrays.asList() 를 이용하면, 배열을 List 로 만들 수 있다. 주의.. 배열 인스턴스 그 자체가 List 내부에서 사용됨..")
    @Test
    void test3() {

        Integer[] array = {1, 2, 3};
        List<Integer> list = Arrays.asList(array); // array 를 포장해서 list 가 만들어짐..

        array[2] = 100;

        System.out.println("Class = " + list.getClass() + ", " + list);
    }
}
