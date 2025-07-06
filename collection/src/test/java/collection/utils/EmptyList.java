package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class EmptyList {

    @DisplayName("Collections.emptyList() 를 이용하여 빈 불변 리스트를 생성할 수 있다. List.of() 를 사용하자.")
    @Test
    void test1() {

        List<Integer> list1 = Collections.emptyList(); // Java 5
        List<Integer> list2 = List.of(); // Java 9

        System.out.println("Class = " + list1.getClass() + ", " + list1);
        System.out.println("Class = " + list2.getClass() + ", " + list2);
    }
}
