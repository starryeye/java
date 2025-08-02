package concurrent.sub5_collection.sub1_explain;

public class Concurrent {

    /**
     * java.util 에 존재하는 Collection 시리즈들은 메서드들 마다 여러 연산들을 포함하기 때문에
     * 동시성 문제가 존재한다. (thread safe 하지 않음)
     *
     * 그래서 제공되는 방법은 각 collection 객체 마다 프록시로 synchronized 를 적용시키는 방법이 있다.
     * 이를 위해서는 아래와 같이 사용한다.
     *      Collections.synchronizedList(new ArrayList<>());
     *      Collections.synchronizedMap ...
     *      Collections.synchronizedSet ...
     *      Collections.synchronizedCollection ...
     *      ...
     * 하지만, 위 방법은 모든 메서드에 synchronized 키워드를 프록시 패턴을 통해 적용시키므로 최적화의 문제가 존재한다.
     *      메서드 전체를 굳이 임계영역으로 지정할 필요가 없음에도 전체를 임계영역으로 지정하여 임계영역의 최적화가 이루어지지 않는..
     *
     * 그래서, java.util 에 존재하는 Collection 시리즈들을 상속받아 동시성 문제를 해결하고
     * 온갖 최적화 기법을 도입하여 구현한 시리즈가 바로..
     *      synchronized, Lock(ReentrantLock), CAS, 분할 잠금 기술(segment lock) 등
     * java.util.concurrent 에 존재하는 시리즈들이다.
     *
     *
     * 동시성 문제를 해결한 객체 매핑 표 (java.util.concurrent 에 존재하는 시리즈)
     * ArrayList
     *      CopyOnWriteArrayList
     *
     * HashSet
     *      CopyOnWriteArraySet
     * TreeSet
     *      ConcurrentSkipListSet
     *
     * HashMap
     *      ConcurrentHashMap
     * TreeMap
     *      ConcurrentSkipListMap
     *
     * Queue
     *      ConcurrentLinkedQueue
     * Deque
     *      ConcurrentLinkedDeque
     *
     * 참고.
     * LinkedHashSet, LinkedHashMap 에 대응되는 객체는 제공되지 않으며, 필요하다면 Collections.synchronizedXXX 로 사용하자.
     *
     *
     *
     */
}
