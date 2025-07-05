package collection.map;

import java.util.*;

public class JavaMapTest {

    /**
     * Map 의 구현체 특징
     * 평균 시간복잡도
     * HashMap
     *      put, remove, containsKey, get -> O(1)
     * LinkedHashMap
     *      put, remove, containsKey, get -> O(1)
     * TreeMap
     *      put, remove, containsKey, get -> O(log n)
     *
     * 참고.
     *      containsValue 의 경우 O(n) 이다.
     */

    public static void main(String[] args) {
        run(new HashMap<>());               // key 중복 X, 순서 보장 X         // c++, unordered_map
        run(new LinkedHashMap<>());         // key 중복 X, 입력 순서 보장       // c++, 대응되는 자료구조 없음
        run(new TreeMap<>());               // key 중복 X, key 정렬 상태 유지   // c++, map
    }

    private static void run(Map<String, Integer> map) {
        System.out.println("map = " + map.getClass());
        map.put("C", 10);
        map.put("B", 20);
        map.put("A", 30);
        map.put("1", 40);
        map.put("2", 50);

        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.print(key + "=" + map.get(key) + " ");
        }
        System.out.println();
    }
}