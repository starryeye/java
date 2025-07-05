package iterable;

import java.util.*;

public class JavaIterableTest {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        HashSet<String> set = new HashSet<>();
        set.add("AA");
        set.add("BB");
        set.add("CC");

        /**
         * Collection 은 Iterable 을 상속하므로, Iterator 를 반환하는 iterator() 메서드가 존재한다.
         * 각 자료구조의 중첩 클래스로 Iterator 를 구현한 클래스가 존재한다.
         */
        printAll(list.iterator());
        printAll(set.iterator());

        foreach(list);
        foreach(set);
    }

    private static void printAll(Iterator<?> iterator) {
        System.out.println("Iterator : " + iterator);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void foreach(Iterable<?> iterable) {
        System.out.println("Iterable : " + iterable);

        // enhanced for loop 는 컴파일러에 의해 아래 주석 처리된 코드로 변형된다.
        for (Object object : iterable) {
            System.out.println(object);
        }

//        while (iterable.iterator().hasNext()) {
//            System.out.println(iterable.iterator().next());
//        }
    }
}
