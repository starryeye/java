package util.iterable;

import java.util.Iterator;

public class MyArray implements Iterable<Integer> {

    /**
     * Iterable..
     * Java 의 Collection 은 Iterable 을 상속한다.
     * iterator() 메서드는 자기 자신 자료구조를 순회 할 수 있게 구현한 Iterator 를 반환한다.
     */

    private int[] numbers;

    public MyArray(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyArrayIterator(numbers);
    }

}