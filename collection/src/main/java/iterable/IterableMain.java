package iterable;

public class IterableMain {

    /**
     * 데이터를 차례대로 접근해서 처리하는 것을 "순회"라고한다.
     * 다양한 자료구조마다 데이터를 접근하는 방법이 모두 다른데, 일관성 있게 동일한 방법으로 처리하기 위해 나온 것이..
     * Iterable, Iterator 이다.
     *
     * Iterable<T> interface
     *      Iterable<T> 을 구현한 객체는 T 타입 element 를 "순회 가능한" 객체라는 뜻이다.
     *          Collection 인터페이스는 Iterable 을 상속한다.
     *      추상 메서드
     *          Iterator<T> iterator() : Iterator 를 반환
     *
     * Iterator<T> interface
     *      Iterator<T> 를 구현한 객체는 T 타입 element 를 가진 자료구조를 순회할 수 있게 해주는 객체이다.
     *      추상 메서드
     *          boolean hasNext() : 다음 순서의 element 가 존재하는지 여부 반환
     *          Integer next() : 다음 순서의 element 반환
     *
     * XXX 자료구조는 Collection 을 구현하였기 때문에, Iterable 도 구현한다.
     *      Iterable 을 구현했으므로 순회 가능한 자료구조라는 뜻이다.
     * XXX 자료구조 내부에 중첩 클래스로 Iterator 가 존재한다.
     *      Iterator 는 iterable 구현 메서드인 iterator() 메서드의 반환 값이다.
     * XXX 자료구조는 iterator() 반환 값인 Iterator 로 순회할 수 있다.
     *
     * 참고.
     * Enhanced For loop 는 해당 객체가 배열이거나 Iterable 을 구현한 객체라면 사용가능하다.
     */
}
