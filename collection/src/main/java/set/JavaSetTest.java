package set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class JavaSetTest {

    public static void main(String[] args) {

        /**
         * Set 의 구현체 특징
         * HashSet
         *      내부 배열의 기본 capacity 는 16 이다.
         *      원소 수가 capacity 의 75% 가 넘어가면 통계적으로 해시 충돌이 많이 발생하여 성능이 나빠진다.
         *          75% 가 넘어가면, capacity 를 2배 키우고 모든 원소의 해시 인덱스를 다시 계산하고 재배치한다. (rehashing 과정)
         *      내부 배열의 원소는 LinkedList<E> 이지만, 많은 원소들의 해시 인덱스가 동일하여 동일한 bucket 에 적재 되면, O(n) 의 성능이 나온다..
         *          이 경우, Java 는 내부 최적화에 의해 LinkedList<E> 를 Tree 구조로 변경 시켜 O(log n) 성능이 되게끔 한다.
         *
         * 평균 시간복잡도
         * HashSet
         *      add, remove, contains -> O(1)
         * LinkedHashSet
         *      add, remove, contains -> O(1)
         * TreeSet
         *      add, remove, contains -> O(log n)
         *
         */
        run(new HashSet<>());           // 원소 중복 X, 순서 보장 X         // c++, unordered_set
        run(new LinkedHashSet<>());     // 원소 중복 X, 입력 순서 보장       // c++, 대응되는 자료구조 없음
        run(new TreeSet<>());           // 원소 중복 X, 원소 정렬 상태 유지   // c++, set
    }

    private static void run(Set<String> set) {
        System.out.println("\nset = " + set.getClass());

        set.add("C");
        set.add("B");
        set.add("A");
        set.add("1");
        set.add("2");

        for (String s : set) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
