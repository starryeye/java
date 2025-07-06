package comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JavaCollectionSortTest {

    public static void main(String[] args) {
        /**
         * Java 의 배열은 Arrays.sort() 를 이용하여 정렬이 가능하였다.
         * Collection 은 자체적으로 sort() 메서드를 제공한다. 이를 이용해 정렬 가능
         *      예시. List::sort()
         *
         * 참고.
         * Collections.sort() 라는 메서드를 이용할 수 도 있는데
         * SRP 관점에서 각 자료구조에 sort() 메서드가 있는 것이 자연스럽고
         * 자기자신의 element 를 정렬한다는 최적화 관점도 있으므로 각 자료구조가 제공하는 sort() 메서드를 이용하도록 하자.
         */

        System.out.println("\n기본 제공 reference type element 를 담고 순서가 존재하는 Collection");
        ArrayList<Integer> arrayList1 = new ArrayList<>(); // 기본 제공 reference type element 의 경우 Comparable 을 구현되어 있어서 해당 구현에 따라 정렬 가능
        arrayList1.add(3);
        arrayList1.add(2);
        arrayList1.add(1);
        System.out.println("original = " + arrayList1);

        arrayList1.sort(null); // null 을 전달하면 natural ordering sort 됨
//        Collections.sort(arrayList1); // 각 자료구조에서 제공하는 sort 를 사용하도록 하자.
        System.out.println("natural ordering sorted, List::sort(null) = " + arrayList1);

        arrayList1.sort(Comparator.reverseOrder()); // Comparator 로 정렬
        System.out.println("reversed natural ordering sorted, List::sort(new Comparator()) = " + arrayList1);


        System.out.println("\n커스텀 reference type element 를 담고 순서가 존재하는 Collection");
        ArrayList<Item> arrayList2 = new ArrayList<>(); // 커스텀 reference type element 는 Comparable 을 구현하면 natural ordering sort 를 이용할 수 있다.
        arrayList2.add(new Item("AAA", 30));
        arrayList2.add(new Item("BBB", 20));
        arrayList2.add(new Item("CCC", 10));
        System.out.println("original = " + arrayList2);

        arrayList2.sort(null); // null 을 전달하면 natural ordering sort 됨 // Comparator.naturalOrder() 과 동일함.
        System.out.println("natural ordering sorted, List::sort(null) = " + arrayList2);

        arrayList2.sort(Comparator.reverseOrder()); // natural ordering sort 의 역순으로 정렬해준다.
        System.out.println("reversed natural ordering sorted, List::sort(new Comparator()) = " + arrayList2);

        arrayList2.sort(((o1, o2) -> o1.getName().compareTo(o2.getName()))); // Item 의 name 필드를 기준으로 정렬할 것이고 String::compareTo 를 사용하여 오름 차순이다.
        System.out.println("name field ascending ordering sorted, List::sort(new Comparator()) = " + arrayList2);
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

        // 커스텀 reference type 은 natural ordering sort 를 이용하기 위해 Comparable 을 구현해야한다.
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
