package collection.arraylist.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListInstanceof {

    @Test
    @DisplayName("ArrayList 는 List, Collection, Iterable 의 인스턴스이다")
    void test1() {
        ArrayList<String> list = new ArrayList<>();

        assertThat(list).isInstanceOf(List.class);
        assertThat(list).isInstanceOf(Collection.class);
        assertThat(list).isInstanceOf(Iterable.class);
        assertThat(list).isInstanceOf(Object.class);
    }

    @Test
    @DisplayName("ArrayList는 Cloneable, Serializable 인터페이스를 구현한다")
    void test2() {
        ArrayList<String> list = new ArrayList<>();

        assertThat(list).isInstanceOf(Cloneable.class);
        assertThat(list).isInstanceOf(Serializable.class);
    }

    @Test
    @DisplayName("ArrayList 는 RandomAccess 인터페이스를 구현한다")
    void test3() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThat(list).isInstanceOf(RandomAccess.class); // RandomAccess, 표시용 marker interface 로 index 접근이 O(1) 임을 나타낸다.
        // LinkedList 의 경우.. index 접근이 O(n) 으로 RandomAccess 구현체가 아님.
    }
}
