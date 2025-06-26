package set.treeset;

import set.MySet;

public class MyTreeSet<E> implements MySet<E> {

    /**
     * binary search tree 를 개선한 red-black tree 를 내부적으로 사용한다.
     * 따라서, 원소들이 정렬된 순서로 유지된다.
     * 주요 연산 시간 복잡도 : O(log n)
     *
     */

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }
}
