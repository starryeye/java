package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffle {

    @DisplayName("Collections.shuffle() 은 전달된 Collection 을 랜덤으로 순서를 섞어준다.")
    @Test
    void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("original = " + list);

        Collections.shuffle(list);
        System.out.println("Collections.shuffle(list) = " + list);

        Collections.shuffle(list);
        System.out.println("Collections.shuffle(list) = " + list);
    }
}
