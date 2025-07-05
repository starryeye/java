package comparable;

import java.util.Arrays;
import java.util.Comparator;

public class JavaArrayComparatorTest1 {

    public static void main(String[] args) {

        /**
         * 배열을 정렬하기 위해서는 Arrays.sort() 메서드를 사용한다.
         *
         * Comparator<T> 없이 사용하려면
         *      배열 element 타입(T)이 Comparable<T> 을 구현해야하고 compareTo 메서드를 사용하여 정렬하게 된다.(Integer::compareTo() 은 오름차순)
         *      natural ordering 이라 부른다.
         * 어떤 Comparator 에 reversed() 를 하면 정반대의 결과가 나온다.
         */

        Integer[] array = new Integer[]{3, 2, 1};
        System.out.println("original = " + Arrays.toString(array)); // 3, 2, 1

        // natural ordering (comparator 를 통하지 않고 정렬, 오름차순)
        Arrays.sort(array);
        System.out.println("natural ordering sorted = " + Arrays.toString(array)); // 1, 2, 3


        // 내림차순 정렬 Comparator<Integer> 을 통해 정렬
        Arrays.sort(array, new DescComparator()); // new DescComparator() 는 new AscComparator().reversed() 와 같은 의미이다.
        System.out.println("descending ordering sorted = " + Arrays.toString(array)); // 3, 2, 1


        // 오름차순 정렬 Comparator<Integer> 을 통해 정렬
        Arrays.sort(array, new AscComparator()); // new AscComparator() 는 new DescComparator().reversed() 와 같은 의미이다.
        System.out.println("ascending ordering sorted = " + Arrays.toString(array)); // 1, 2, 3
    }

    private static class AscComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {

            return (o1 < o2) ? -1 : ((o1 == o2) ? 0 : 1);
        }
    }

    private static class DescComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {

            return ((o1 < o2) ? -1 : ((o1 == o2) ? 0 : 1)) * -1;
        }
    }
}
