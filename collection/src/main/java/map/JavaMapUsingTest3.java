package map;

import java.util.HashMap;
import java.util.Map;

public class JavaMapUsingTest3 {

    public static void main(String[] args) {
        Map<String, Integer> studentMap = new HashMap<>();

        // 데이터 추가
        studentMap.put("studentA", 50);
        System.out.println(studentMap);

        // 동일한 key 가 없는 경우에만 Entry 추가
        if (!studentMap.containsKey("studentA")) {
            studentMap.put("studentA", 100);
        }
        System.out.println(studentMap);

        // 동일한 key 가 없는 경우에만 Entry 추가, putIfAbsent()
        studentMap.putIfAbsent("studentA", 100);
        studentMap.putIfAbsent("studentB", 100);
        System.out.println(studentMap);
    }
}
