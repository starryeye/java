package deque;

public interface MyDeque<E> {

    /**
     * Queue 인터페이스는 Collection 을 상속하며..
     * Deque 인터페이스는 Queue 를 상속한다.
     *
     * Deque..
     *
     * 구현체
     *      ArrayDeque
     *          Deque 인터페이스를 구현
     *          ArrayList 처럼 내부에 동적 배열을 가지는 구조이다. (원형 큐 자료구조..)
     *      LinkedList
     *          Deque, List 인터페이스를 모두 구현
     *          LinkedList 처럼 노드끼리 참조 연결의 구조이다.
     *
     * 두 구현체 시간복잡도
     * 앞/뒤 입력/삭제 및 access 모두 O(1) 이지만, ArrayDeque 가 더 빠름
     *
     * Queue 로 쓰기
     *      offerLast 로 추가하고 pollFirst 로 꺼내기
     *      offer, poll 메서드도 지원됨 (offerLast, pollFirst 로 구현됨)
     * Stack 으로 쓰기
     *      offerLast 로 추가하고 pollLast 로 꺼내기
     *      push, pop 메서드도 지원됨 (offerFirst, pollFirst 로 구현됨)
     */

    boolean offerFirst(E e); // 앞에 추가
    boolean offerLast(E e); // 뒤에 추가
    E pollFirst(); // 앞에서 꺼낸다
    E pollLast(); // 뒤에서 꺼낸다

    E peekFirst(); // 앞 원소 확인
    E peekLast(); // 뒤 원소 확인
}
