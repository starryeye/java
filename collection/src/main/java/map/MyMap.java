package map;

import java.util.Collection;
import java.util.Map;
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
     *
     * Map 의 Key 부분만 생각하면 Set 과 거의 같음..
     *      Set 의 구현은 Map 의 구현을 가져다 쓰며, value 에는 더미 값을 채운채로 구현됨.
     *      key 값을 기준으로 해시 코드, 해시 인덱스를 만들어서 저장할 위치를 정하고 Entry 구조(key - value 쌍)로 저장한다.
     *          key 로 사용되는 객체는 equals(), hashCode() 를 반드시 오버라이딩해야한다.
     */

    boolean put(K key, V value);
    V get(K key);
    boolean remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    Set<K> keySet();
    Collection<V> values();
    Set<Map.Entry<K, V>> entrySet();
    int size();
}
