package comparable;

import java.util.Arrays;
import java.util.Comparator;

public class JavaArrayComparatorTest2 {

    public static void main(String[] args) {

        /**
         * Java 가 제공하는 reference type 객체는 Comparable 을 구현하기 때문에 natural ordering 정렬이 가능하다.
         *      Comparable 을 구현하지 않더라도 Comparator 를 제공하면 정렬이 가능하긴 하다.
         *
         * 개발자가 직접 만든 객체는 어떤 기준으로 정렬을 해야할 지 모르기 때문에
         *      Comparable 을 구현하지 않으면, natural ordering 정렬이 불가능하다.
         */

        Item[] items = {
                new Item("AAA", 30),
                new Item("BBB", 20),
                new Item("CCC", 10)
        };
        System.out.println("original = " + Arrays.toString(items)); // 입력 순 출력

        // Item 은 Comparable 을 구현하여 natural ordering 정렬을 사용할 수 있다. (compareTo() 에서 quantity 의 오름차순으로 구현)
        Arrays.sort(items);
        System.out.println("natural ordering sorted = " + Arrays.toString(items));

        // Comparator.reverseOrder() 를 이용하면, 기본 정렬의 역순으로 정렬 된다.
        Arrays.sort(items, Comparator.reverseOrder());
        System.out.println("reversed natural ordering sorted = " + Arrays.toString(items));

        // 람다를 이용하여 Comparator 를 만들어서 정렬한다. (name 필드의 내림차순 정렬)
        Arrays.sort(items, (o1, o2) -> {
            return o1.getName().compareTo(o2.getName()) * -1; // String 의 기본 compareTo() 는 오름차순인데 -1 을 곱하여 역순으로 만듬
        });
        System.out.println("name field ascending ordering sorted = " + Arrays.toString(items));
    }

    private static class Item implements Comparable<Item> {

        private final String name;
        private final int quantity;

        public Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Item o) {
            return this.quantity < o.quantity ? -1 : this.quantity > o.quantity ? 1 : 0;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
