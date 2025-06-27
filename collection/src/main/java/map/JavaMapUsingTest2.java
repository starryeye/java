package map;

import java.util.HashMap;
import java.util.Map;

public class JavaMapUsingTest2 {

    public static void main(String[] args) {
        Map<String, Integer> studentMap = new HashMap<>();

        // 데이터 추가
        studentMap.put("studentA", 90);
        System.out.println(studentMap);

        studentMap.put("studentA", 100); // 같은 key 로 저장하면 기존 value 가 교체된다.
        System.out.println(studentMap);

        boolean containsKey = studentMap.containsKey("studentA"); // 특정 key 존재여부 search
        System.out.println("containsKey = " + containsKey);

        // 특정 entry 삭제
        studentMap.remove("studentA");
        System.out.println(studentMap);
    }
}