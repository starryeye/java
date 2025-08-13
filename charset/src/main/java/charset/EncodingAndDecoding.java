package charset;

import java.nio.charset.Charset;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingAndDecoding {

    /**
     * 여러 경우의 Charset 들로
     * 특정 문자그림을 인코딩하여 컴퓨터 메모리상에서 저장되는 이진수로 변환해보고
     * 컴퓨터 메모리상에서 저장되는 이진수를 다시 디코딩하여 특정 문자그림으로 출력해본다.
     */

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 ==");
        test("A", US_ASCII, ISO_8859_1);        // ISO_8859_1 는 ASCII 를 확장한 Charset 이라 호환됨
        test("A", US_ASCII, EUC_KR);            // EUC_KR 는 ASCII 를 확장한 Charset 이라 호환됨
        test("A", US_ASCII, MS_949);            // MS_949 는 ASCII 를 확장한 Charset 이라 호환됨
        test("A", US_ASCII, UTF_8);             // UTF_8 는 ASCII 를 확장한 Charset 이라 호환됨
        test("A", US_ASCII, UTF_16BE);          // 호환되지 않음, UTF_16 는 2byte 사용하고 ASCII 는 1byte 로 사용됨. 따라서 디코딩 실패
        test("A", EUC_KR, UTF_8);               // 호환됨, 둘다 ASCII 를 확장
        test("A", MS_949, UTF_8);               // 호환됨, 둘다 ASCII 를 확장
        test("A", UTF_8, UTF_16BE);             // 호환되지 않음, (US_ASCII, UTF_16BE) 케이스와 동일

        System.out.println("== 한글 ==");
        test("가", US_ASCII, US_ASCII);          // ASCII 에는 한글문자가 포함되어 있지 않아 인코딩 실패 (인코딩 실패시 "?" 로 표현됨)
        test("가", ISO_8859_1, ISO_8859_1);      // ISO_8859_1 에는 한글문자가 포함되어 있지 않아 인코딩 실패 (인코딩 실패시 "?" 로 표현됨)
        test("가", EUC_KR, MS_949);              // MS_949 는 EUC_KR 를 확장한 Charset 이라 호환됨
        test("가", EUC_KR, UTF_8);               // 호환되지 않음, EUC_KR 는 2byte 사용하고 UTF_8 는 3byte 로 사용됨. 따라서 디코딩 실패
        test("가", EUC_KR, UTF_16);              // 호환되지 않음, EUC_KR 는 2byte 사용하고 UTF_16 도 2byte 로 사용되지만, 서로다른 매핑테이블이라 디코딩 실패
        test("가", MS_949, UTF_8);               // 호환되지 않음, MS_949 는 2byte 사용하고 UTF_8 는 3byte 로 사용됨. 따라서 디코딩 실패

        System.out.println("== 한글 - 복잡한 문자 ==");
        test("뷁", EUC_KR, EUC_KR);              // 인코딩 실패, 동일한 Charset 으로 사용하였지만, EUC-KR 은 많이 사용되는 한글문자만 지원하므로 "뷁" 문자그림은 지원안됨
        test("뷁", MS_949, MS_949);              // MS_949 에서는 한글로 만들수 있는 모든 한글문자를 지원
        test("뷁", UTF_8, UTF_8);                // UTF_8 에서는 한글로 만들수 있는 모든 한글문자를 지원
        test("뷁", UTF_16BE, UTF_16BE);          // UTF_16BE 에서는 한글로 만들수 있는 모든 한글문자를 지원
        test("뷁", MS_949, EUC_KR);              // 인코딩 성공, 디코딩 실패 (EUC-KR 은 많이 사용되는 한글문자만 지원하므로 "뷁" 문자그림은 지원안됨)
        
    }

    private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
        byte[] encoded = text.getBytes(encodingCharset);
        String decoded = new String(encoded, decodingCharset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s\n",
                text, encodingCharset, Arrays.toString(encoded), encoded.length,
                decodingCharset, decoded);
    }
}
