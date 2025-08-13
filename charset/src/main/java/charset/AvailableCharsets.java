package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsets {

    /**
     * Charset..
     * 사람이 화면에서 보는 문자와 컴퓨터 메모리에서 사용되는 값을 매핑 시키는 테이블이다.
     *      그림과 메모리 값(숫자)를 매핑 시킨 테이블이다.
     *
     * 인코딩
     * 회면에 표시되는 문자그림 -> 인코딩 -> 컴퓨터 메모리나 디스크에 저장될 때의 값
     *
     * 디코딩
     * 컴퓨터 메모리나 디스크에 저장될 때의 값 -> 디코딩 -> 회면에 표시되는 문자그림
     *
     * 동일한 Charset 을 사용하지 않으면..
     * 인코딩 측면에서, 동일한 문자그림이라도 서로 다른 메모리 값이 사용될 수 도 있고 같은 값이 사용될 수 도 있다. (같은 값이 사용된다면 호환이 되는 것)
     * 디코딩 측면에서, 동일한 메모리 값이라도 서로 다른 문자그림이 사용될 수 도 있고 같은 문자그림이 사용될 수 도 있다. (같은 문자그림이 사용된다면 호환이 되는 것)
     *      서로 다른 문자그림이 매핑되면 글자가 깨져보인다.
     *
     * 참고.
     * 사람이 화면에서 보는 문자는 컴퓨터 입장에서는 의미 없는 그림일 뿐이다.
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
