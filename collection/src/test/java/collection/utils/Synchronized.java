package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Synchronized {

    @DisplayName("Collections.synchronizedList() 를 사용하면, 일반 리스트를 멀티 스레드 환경에서 안전한 리스트로 만들 수 있다.")
    @Test
    void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("class = "+ list.getClass() + ", " + list);

        List<Integer> synchronizedList = Collections.synchronizedList(list);
        System.out.println("class = "+ synchronizedList.getClass() + ", " + synchronizedList);
    }
}
