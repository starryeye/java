package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Encoding {

    /**
     * 인코딩/디코딩에 사용되는 Charset 에 따라
     * 알파벳 "A" 와 한글 문자 "가" 가 메모리상에서 어떤 값으로 저장되는지 알아본다.
     *
     * 참고
     * intellij 콘솔 로그화면에서 한글 문자가 깨져 보이면,
     * build and run 을 Gradle 이 아닌 Intellij 로 해보자
     */

    public static void main(String[] args) {

        // 알파벳은 UTF-16 를 제외하고는 모두 1byte 로 지원한다.
        System.out.println("== English alphabet  =="); // 알파벳
        encode("A", StandardCharsets.US_ASCII);
        encode("A", StandardCharsets.ISO_8859_1);
        encode("A", Charset.forName("EUC-KR"));
        encode("A", StandardCharsets.UTF_8);
        encode("A", StandardCharsets.UTF_16BE); // 2byte

        // 한글 문자는 UTF-8 에서는 3byte 로 지원되고 나머지는 2byte 로 지원한다.
        System.out.println("== korean character =="); // 한글 문자
        encode("가", Charset.forName("EUC-KR"));
        encode("가", Charset.forName("MS949"));
        encode("가", StandardCharsets.UTF_8);
        encode("가", StandardCharsets.UTF_16BE);

    }

    private static void encode(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset); // 주어진 Charset 으로 문자를 인코딩 해본다.

        System.out.printf(
                "%4s -> [%15s] encoding.. -> %s %sbyte, memory : %s%n",
                text,
                charset.displayName(),
                Arrays.toString(bytes),
                bytes.length,
                toBinaryString(bytes)
        );
    }

    private static String toBinaryString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'))
                    .append(' ');
        }
        return sb.toString().trim();
    }
}
