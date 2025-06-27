package set.treeset;

import set.MySet;

public class MyTreeSet<E> implements MySet<E> {

    /**
     * binary search tree 를 개선한 red-black tree 를 내부적으로 사용한다. (지속적으로 트리의 균형을 유지하도록 개선)
     * 따라서, 원소들이 정렬된 순서로 유지된다. (Comparator)
     * 주요 연산 시간 복잡도 : O(log n)
     *      트리의 균형을 유지시켜서 시간복잡도를 보장시킴
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
