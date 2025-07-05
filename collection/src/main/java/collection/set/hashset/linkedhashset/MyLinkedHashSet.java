package collection.set.hashset.linkedhashset;

import set.hashset.MyHashSet;

public class MyLinkedHashSet<E> extends MyHashSet<E> {

    /**
     * HashSet 을 상속 받는 구조이다.
     * Set 이지만, 순서를 보장한다는게 특징이다.. (입력된 순서)
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
