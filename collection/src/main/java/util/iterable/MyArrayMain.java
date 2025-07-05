package util.iterable;

import java.util.Iterator;

public class MyArrayMain {

    public static void main(String[] args) {

        MyArray myArray = new MyArray(new int[]{1, 2, 3, 4});

        Iterator<Integer> iterator = myArray.iterator();

        System.out.println("iterator 를 사용하여 순회할 수 있다.");
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println("value = " + value);
        }

        // array 이거나 Iterable 을 구현한 객체는 아래와 같은 문법(enhanced for loop)을 사용할 수 있다.
        System.out.println("for-each(enhanced for loop) 사용");
        for (int value : myArray) {
            System.out.println("value = " + value);
        }
    }
}
