package collection.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Of {

    @DisplayName("XXX.of() 를 사용하면 불변 컬렉션이 만들어진다. element 추가, 삭제, 변경 불가능하며 조회만 가능하다.")
    @Test
    void test1() {
        List<Integer> list = List.of(1, 2, 3);
        Set<String> set = Set.of("AA", "BB", "CC");
        Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");

        System.out.println("Class = " + list.getClass() + ", " + list);
        System.out.println("Class = " + set.getClass() + ", " + set);
        System.out.println("Class = " + map.getClass() + ", " + map);

        assertThatThrownBy(() -> list.add(4))
                .isInstanceOf(UnsupportedOperationException.class); // CUD 시도하면 예외 발생
    }

    @DisplayName("XXX.of() 를 이용하면, 배열을 List 로 만들 수 있다.")
    @Test
    void test2() {

        Integer[] array = {1, 2, 3};
        List<Integer> list = List.of(array);

        System.out.println("Class = " + list.getClass() + ", " + list);
    }
}
