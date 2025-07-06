package comparable;

import java.util.Arrays;
import java.util.Comparator;

public class JavaArraySortTest1 {

    public static void main(String[] args) {

        /**
         * 배열을 정렬하기 위해서는 Arrays.sort() 메서드를 사용한다.
         *      void sort(Object[] a)
         *      void sort(T[] a, Comparator<? super T> c)
         *
         *
         * Comparator<T> 없이 사용하려면
         *      배열 element 타입(T)이 Comparable<T> 을 구현해야하고 compareTo 메서드를 사용하여 정렬하게 된다.(Integer::compareTo() 은 오름차순)
         *      natural ordering 이라 부른다.
         * 어떤 Comparator 에 reversed() 를 하면 정반대의 결과가 나온다.
         *
         * 참고.
         * primitive type 의 element 는 Comparable 을 구현하지 못한다.
         * 하지만, Arrays.sort() 를 이용하여 정렬이 가능한데 기본 오름차순으로 하드코딩된 정렬 알고리즘을 따른다.
         *      void sort(int[] a) 이와 같이 각 primitive type 에 맞는 메서드를 따로 제공함.
         * 또한, Comparator<T> 를 이용한 정렬도 타입 파라미터에 primitive type 을 사용하지 못해 이용이 불가능하다.
         * 따라서, 커스텀 정렬이 필요할 경우 boxed 하여 정렬해야한다.
         *
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
