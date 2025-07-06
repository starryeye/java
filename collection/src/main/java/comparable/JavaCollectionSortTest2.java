package comparable;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class JavaCollectionSortTest2 {

    public static void main(String[] args) {

        /**
         * Set, Map 은 순서가 없지만, TreeSet, TreeMap 은 자체 정렬을하여 순서가 유지되는 자료구조이다. (순서 존재)
         *
         * TreeSet, TreeMap 의 경우 생성자 파라미터로 Comparator 를 전달하여 해당 Comparator 로 정렬이 유지되도록 할 수 있다.
         *      전달하지 않으면..
         *          reference type element 의 경우엔 Comparable 구현으로 정렬된다.
         *          primitive type element 의 경우에는 TreeSet, TreeMap 의 타입 파라미터로 사용이 불가능하므로 애초에 사용 불가
         */

        Set<Integer> set1 = new TreeSet<>(); // 기본 생성자 이용, natural ordering sort 된다.
        set1.add(3);
        set1.add(2);
        set1.add(1); // 내림차순으로 element add
        System.out.println("natural ordering sorted = " + set1);

        Set<Integer> set2 = new TreeSet<>(Comparator.reverseOrder()); // 생성자 파라미터로 Comparator 전달
        set2.add(1);
        set2.add(2);
        set2.add(3); // 오름차순으로 element add
        System.out.println("reversed natural ordering sorted = " + set2);
    }
}
