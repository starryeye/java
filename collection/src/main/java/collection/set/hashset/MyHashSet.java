package collection.set.hashset;

import collection.set.MySet;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashSet<E> implements MySet<E> {

    private static final int DEFAULT_CAPACITY = 16;

    private LinkedList<E>[] buckets; // LinkedList<E> 원소를 담을 수 있는 배열 선언

    private int size;
    private int capacity = DEFAULT_CAPACITY;

    public MyHashSet() {
        initializeBuckets();
    }

    public MyHashSet(int capacity) {
        this.capacity = capacity;
        initializeBuckets();
    }

    @Override
    public boolean add(E e) { // O(1)

        int hashedIndex = hashIndex(e);
        LinkedList<E> bucket = buckets[hashedIndex];
        if (bucket.contains(e)) {
            return false; // Set 에서는 중복 원소가 불가하다.
        }

        bucket.add(e); // 서로 다른 값이라도 해시 코드가 동일해서 해시 충돌이 일어날 수 있지만, 충돌을 인정하고 LinkedList 에 넣어준다.
        size++;
        return true;
    }

    @Override
    public boolean remove(E e) { // O(1)
        int hashedIndex = hashIndex(e);
        LinkedList<E> bucket = buckets[hashedIndex];
        boolean remove = bucket.remove(e);

        if (!remove) {
            return false;
        }

        size--;
        return true;
    }

    @Override
    public boolean contains(E e) { // O(1)
        int hashedIndex = hashIndex(e);
        LinkedList<E> bucket = buckets[hashedIndex];
        return bucket.contains(e); // 해시 충돌이 일어난 상황 이면, LinkedList 에 여러 값이 있을 수 있고 이 과정에서 순차적으로 equals() 비교를 통해 찾는다.
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyHashSet{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    private void initializeBuckets() {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hashIndex(E value) {
        // 해시 함수를 통해 해시 코드를 구하고 나머지 연산을 통해 해시 코드로 해시 인덱스를 구한다.
        return Math.abs(value.hashCode()) % capacity; //hashCode 의 결과로 음수가 나올 수 있어서 절댓값 처리
    }
}
