package set.hashset;

import set.MySet;

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

        bucket.add(e);
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
        return bucket.contains(e);
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

    /**
     * 해시 함수
     *      임의의 길이의 데이터를 입력으로 받아, 고정된 크기의 해시값(해시 코드)을 반환하는 함수
     *          int 형 반환의 경우 고정된 크기는 4 byte 이다.
     *
     * 해시 코드
     *      해시 함수를 통해 만들어지는 값으로, 데이터를 대표하는 값이다.
     *
     * 해시 인덱스
     *      데이터를 저장할 위치를 결정하는 인덱스 값이다.
     *      보통 해시 코드를 배열 크기로 나눈 나머지 값을 사용한다.
     *
     * 원본 데이터 -> (해시 함수) -> 해시 코드
     * 해시 코드 -> (해시 인덱스 함수) -> 해시 인덱스
     * 배열[해시 인덱스] = 원본 데이터
     *      -> contains() 를 O(1) 로 만들 수 있다. (search)
     */

}
