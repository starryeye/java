package list.linkedlist;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<String> list = new MyLinkedList<>();

        // addFirst / addLast
        list.addFirst("C");
        list.addFirst("B");
        list.addFirst("A"); // A ⇄ B ⇄ C
        list.addLast("D");  // A ⇄ B ⇄ C ⇄ D

        System.out.println("After addFirst and addLast:");
        System.out.println(list);

        // add(index, value)
        list.add(2, "X"); // A ⇄ B ⇄ X ⇄ C ⇄ D
        System.out.println("After add(2, \"X\"):");
        System.out.println(list);

        // set(index, value)
        list.set(3, "Y"); // A ⇄ B ⇄ X ⇄ Y ⇄ D
        System.out.println("After set(3, \"Y\"):");
        System.out.println(list);

        // get(index), getFirst(), getLast()
        System.out.println("Element at index 2: " + list.get(2));       // X
        System.out.println("First element: " + list.getFirst());        // A
        System.out.println("Last element: " + list.getLast());          // D

        // indexOf
        System.out.println("Index of 'Y': " + list.indexOf("Y"));       // 3
        System.out.println("Index of 'Z': " + list.indexOf("Z"));       // -1

        // remove(index)
        list.remove(1); // remove "B"
        System.out.println("After remove(1):");
        System.out.println(list);

        // removeFirst / removeLast
        list.removeFirst(); // remove "A"
        list.removeLast();  // remove "D"
        System.out.println("After removeFirst and removeLast:");
        System.out.println(list);

        // size
        System.out.println("Size of list: " + list.size());

        // clear
        list.clear();
        System.out.println("After clear:");
        System.out.println(list);
        System.out.println("Size of list: " + list.size());
    }
}
