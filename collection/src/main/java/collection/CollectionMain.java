package collection;

public class CollectionMain {

    /**
     * Java 의 Collection 은 제네릭 타입 기반이라서 primitive type 을 지원하지 않는다.
     *      Java 의 제네릭은 primitive 타입(int, double, char, ...)을 직접 사용할 수 없음
     *      또한, Hash 자료구조는 hashcode()를 오버라이딩하고 반환 값인 해시 코드를 사용해서 해시 인덱스를 만들어내기 때문에 primitive type 은 더욱 사용이 어려움
     *      따라서, unboxing, boxing 을 이용해서 Wrapper type 을 사용하자
     */

    /**
     * 순서가 중요하고 중복이 허용되는 경우
     *      List 인터페이스룰 사용, ArrayList 가 일반적인 선택이지만, 데이터가 매우 많고 0 index 위치에 추가/삭제가 빈번하면 LinkedList
     *
     * 중복을 허용하지 않고 순서가 중요하지 않은 경우
     *      HashSet 사용, 입력 순서가 필요하면 LinkedHashSet, 정렬된 순서가 필요하면 TreeSet
     *
     * element 를 key - value 쌍으로 저장할 경우
     *      Map 인터페이스를 사용, 순서가 필요 없으면 HashMap, 입력 순서가 필요하면 LinkedHashMap, 정렬된 순서가 필요하면 TreeMap
     *
     * element 에 어떤 처리를 하기전에 보관할 곳을 찾는 경우
     *      Queue, Deque 인터페이스를 사용, 큐/스택 모두 ArrayDeque 를 사용하는 것이 빠르고, 특별한 우선순위가 필요하면 PriorityQueue 사용
     */

    /**
     * 일반적인 선택
     * List -> ArrayList
     * Set -> HashSet
     * Map -> HashMap
     * Queue -> ArrayDeque
     */
}
