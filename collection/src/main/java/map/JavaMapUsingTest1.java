package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaMapUsingTest1 {

    public static void main(String[] args) {
        Map<String, Integer> studentMap = new HashMap<>();

        // 학생 성적 데이터 추가
        studentMap.put("studentA", 90);
        studentMap.put("studentB", 80);
        studentMap.put("studentC", 80);
        studentMap.put("studentD", 100);
        System.out.println(studentMap);

        // 특정 학생의 값 조회
        Integer result = studentMap.get("studentD");
        System.out.println("result = " + result);

        System.out.println("KeySet 활용");
        Set<String> keySet = studentMap.keySet(); // 저장된 key 들을 Set 자료구조로 반환
        for (String key : keySet) {
            Integer value = studentMap.get(key);
            System.out.println("key=" + key + ", value=" + value);
        }

        System.out.println("entrySet 활용");
        Set<Map.Entry<String, Integer>> entries = studentMap.entrySet(); // 저장된 key - value 쌍(Entry 구조)들을 Set 자료구조로 반환
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key=" + key + ", value=" + value);
        }

        System.out.println("values 활용");
        Collection<Integer> values = studentMap.values(); // 저장된 value 들을 Collection 자료구조로 반환 (순서 및 중복 X, 자료의 모음 개념)
        for (Integer value : values) {
            System.out.println("value = " + value);
        }

    }
}