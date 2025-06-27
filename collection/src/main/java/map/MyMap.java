package map;

import java.util.Collection;
import java.util.Set;

public interface MyMap<K, V> {
    /**
     * Map..
     *      key - value 쌍을 저장하는 자료구조
     *      순서를 보장하지 않는다.
     *      key 는 Map 내에서 유일하다. (중복 X)
     *      value 는 Map 내에서 중복될 수 있다.
     *
     * 대표 구현체
     *      HashMap
     *      LinkedHashMap
     *      TreeMap
     *
     * Collection 을 상속하지 않는다.
     *      Set, List 와 다르게 key - value 쌍의 자료구조라 독립적이다.
     */

    boolean put(K key, V value);
    V get(K key);
    boolean remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    Set<K> keySet();
    Collection<V> values();
    int size();
}
