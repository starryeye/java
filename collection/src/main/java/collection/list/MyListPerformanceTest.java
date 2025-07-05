package collection.list;

import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;

public class MyListPerformanceTest {

    public static void main(String[] args) {

        int n = 50_000;

        /**
         * ArrayList
         * 1. index 를 바탕으로 O(1) 에 access 가 가능하다.
         * 2. 특정 index 에 element 를 추가(끼워넣기)를 하면 그 이후 index 들은 +1 shift 작업을 O(n) 걸려서 해야한다.
         * 3. 특정 값이 몇 index 에 존재하는지 검색하는 것은 순회 탐색으로 O(n) 에 가능하다. (정렬되어 있지 않다고 가정)
         */
        System.out.println("\n== MyArrayList :: add / remove ==");
        addFirst(new MyArrayList<>(), n); // 위치 찾기 O(1), element 추가 O(n)
        addMid(new MyArrayList<>(), n); // 위치 찾기 O(1), element 추가 O(n)
        addLast(new MyArrayList<>(), n); // 위치 찾기 O(1), element 추가 O(1)     (last index 에 추가하면 그 이후 index 들이 없어서 +1 shift 할게 없으므로 O(1) 이다.)

        System.out.println("\n== MyArrayList :: access ==");
        MyList<Integer> arrayListForAccess = getInitializedMyArrayList(n);
        access(arrayListForAccess, n, 0); // O(1)
        access(arrayListForAccess, n, arrayListForAccess.size() / 2); // O(1)
        access(arrayListForAccess, n, arrayListForAccess.size() - 1); // O(1)

        System.out.println("\n== MyArrayList :: search ==");
        MyList<Integer> arrayListForSearch = getInitializedMyArrayList(n);
        search(arrayListForSearch, n, 0); // O(1)       (search 는 0 index 부터 순차적으로 값 비교를 하기 때문에 first index 의 값은 O(1) 이 걸린다. best case)
        search(arrayListForSearch, n, arrayListForSearch.size() / 2); // O(n)
        search(arrayListForSearch, n, arrayListForSearch.size() - 1); // O(n)

        /**
         * LinkedList
         * 1. index 를 바탕으로 O(n) 에 access 가 가능하다.
         * 2. 특정 index 에 element 를 추가(끼워넣기)는 O(1) 에 가능하다.
         * 3. 특정 값이 몇 index 에 존재하는지 검색하는 것은 순회 탐색으로 O(n) 에 가능하다. (정렬되어 있지 않다고 가정)
         */
        System.out.println("\n== MyLinkedList :: add ==");
        addFirst(new MyLinkedList<>(), n); // 위치 찾기 O(1), element 추가 O(1)       (first, last index 의 경우에는 내부적으로 doubly linked list 구조로 head, tail 참조 값을 가지기 때문에 O(1) 로 접근 가능)
        addMid(new MyLinkedList<>(), n); // 위치 찾기 O(n), element 추가 O(1)
        addLast(new MyLinkedList<>(), n); // 위치 찾기 O(1), element 추가 O(1)        (first, last index 의 경우에는 내부적으로 doubly linked list 구조로 head, tail 참조 값을 가지기 때문에 O(1) 로 접근 가능)

        System.out.println("\n== MyLinkedList :: access ==");
        MyList<Integer> linkedListForAccess = getInitializedMyLinkedList(n);
        access(linkedListForAccess, n, 0); // O(1)      (first, last index 의 경우에는 내부적으로 doubly linked list 구조로 head, tail 참조 값을 가지기 때문에 O(1) 로 접근 가능)
        access(linkedListForAccess, n, linkedListForAccess.size() / 2); // O(n)
        access(linkedListForAccess, n, linkedListForAccess.size() - 1); // O(1)     (first, last index 의 경우에는 내부적으로 doubly linked list 구조로 head, tail 참조 값을 가지기 때문에 O(1) 로 접근 가능)

        System.out.println("\n== MyLinkedList :: search ==");
        MyList<Integer> linkedListForSearch = getInitializedMyLinkedList(n);
        search(linkedListForSearch, n, 0); // O(1)      (search 는 0 index 부터 순차적으로 값 비교를 하기 때문에 first index 의 값은 O(1) 이 걸린다. best case)
        search(linkedListForSearch, n, linkedListForSearch.size() / 2); // O(n)
        search(linkedListForSearch, n, linkedListForSearch.size() - 1); // O(n)

    }

    private static MyList<Integer> getInitializedMyArrayList(int n) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        return list;
    }

    private static MyList<Integer> getInitializedMyLinkedList(int n) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        return list;
    }

    private static void addFirst(MyList<Integer> list, int n) {

        long start = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            list.add(0, i); // List 0 index 에 element 를 추가한다.
        }

        long end = System.currentTimeMillis();

        System.out.println("[0 index 에 element 추가] 갯수 : " + n + ", elapsed : " + (end - start) + "ms");
    }

    private static void addMid(MyList<Integer> list, int n) {

        long start = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            list.add(i / 2, i); // List 중간 index 에 element 를 추가한다.
        }

        long end = System.currentTimeMillis();

        System.out.println("[List 중간 index 에 element 추가] 갯수 : " + n + ", elapsed : " + (end - start) + "ms");
    }

    private static void addLast(MyList<Integer> list, int n) {

        long start = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            list.add(i); // List 마지막 index 에 element 를 추가한다.
        }

        long end = System.currentTimeMillis();

        System.out.println("[List 마지막 index 에 element 추가] 갯수 : " + n + ", elapsed : " + (end - start) + "ms");
    }

    private static void access(MyList<Integer> list, int n, int index) {

        long start = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            list.get(index);
        }

        long end = System.currentTimeMillis();

        System.out.println("[" + index + "/" + (list.size() - 1) + " index 에 존재하는 element 접근] 반복 횟수 : " + n + ", elapsed : " + (end - start) + "ms");
    }

    private static void search(MyList<Integer> list, int n, int value) {

        long start = System.currentTimeMillis();

        int index = 0;
        for (int i = 0; i < n; i++) {
            index = list.indexOf(value);
        }

        long end = System.currentTimeMillis();

        System.out.println("[특정 value " + value + " 가 몇 index 에 존재하는지 검색, 실제로는 " + index + "/" + list.size() + " 에 존재한 상황] 반복 횟수 : " + n + ", elapsed : " + (end - start) + "ms");
    }
}
