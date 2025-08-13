package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsets {

    /**
     * 현재 아래 코드를 실행하는 환경(OS, Java) 에서
     * 사용 가능한 Charset 을 조회해본다.
     *
     */

    public static void main(String[] args) {

        // 사용 가능 Charset 전체 조회
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets(); // Java 에서 제공하거나 OS 에서 제공하는 Charset 모음을 조회한다.
        for (String key : availableCharsets.keySet()) {
            System.out.println("[Charset.availableCharsets()] (K:" + key + ", V:" + availableCharsets.get(key) + ")");
        }


        // 특정 Charset 조회 1 (정규 이름과 별칭 둘다 조회 가능)
        Charset charset1 = Charset.forName("MS949"); // 정규 이름 x-windows-949 의 별칭 MS949, ms949 도 가능
        System.out.println("charset1: " + charset1);

        // 특정 Charset 의 별칭 조회
        Set<String> aliases = charset1.aliases();
        for (String alias : aliases) {
            System.out.println("[charset1 aliases] " + alias);
        }

        // 자주 사용되는 Charset 은 Java 에서 상수로 제공해준다.
        Charset charset2 = StandardCharsets.UTF_8;
        System.out.println("charset2: " + charset2);

        // 현재 시스템의 기본 Charset 을 조회
        Charset charset3 = Charset.defaultCharset();
        System.out.println("Charset.defaultCharset(): " + charset3);
    }
}
