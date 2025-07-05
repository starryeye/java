package iterable;

import java.util.Iterator;

public class MyArrayIterator implements Iterator<Integer> {

    /**
     * Iterator..
     * 특정 자료구조를 순회할 수 있게 만든 인터페이스이다.
     * hasNext() 로 다음 element 가 존재하는지 알 수 있다.
     * next() 로 다음 element 를 access 할 수 있다.
     */

    private int[] targetArr;

    private int currentIndex = -1;

    public MyArrayIterator(int[] targetArr) {
        this.targetArr = targetArr;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < targetArr.length - 1;
    }

    @Override
    public Integer next() {
        return targetArr[++currentIndex];
    }
}
