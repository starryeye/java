package dev.practice.basic.sub8_http.sub3_percent_encoding_decoding;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PercentEncodingDecoding {

    /**
     * HTTP 의 시작 라인(start line) 과 헤더 영역은 항상 ASCII Charset 을 사용한다.
     *      HTTP 의 body 영역은 UTF-8 과 같은 다른 Charset 을 사용할 수 있다. (Content-Type 으로 표기해주고..)
     *
     * URL 에 한글문자을 넣으면.. 퍼센트 인코딩을 사용한다. (ex. http://localhost:8080/search?q=가나)
     *
     * percent encoding 에 대해 알아본다.
     * 특징
     *      한글문자는 UTF-8 charset 으로 인코딩 후, 앞에 % 를 붙인다. (참고. 한글은 인코딩후 1 글자당 3byte 를 가진다.)
     *      영어문자는 percent encoding 을 해도 그대로다.
     *      예시는 아래 코드 참고
     * 동작 과정
     * 1. client 가 server 로 "가" 를 쿼리파라미터로 전달 시도
     *      http://localhost:8080/search?q=가
     * 2. client 는 한글문자("가")을 퍼센트 인코딩을 한다.
     *      2-1. "가" 를 UTF-8 로 인코딩
     *              -> EA, B0, 80 (3byte) 획득 (16진수로 표현함, 원본은 2진수임)
     *      2-2. 각 byte 를 16진수 문자로 표현후 앞에 % 를 붙인다.
     *              -> %EA%B0%80
     * 3. client 는 server 로 요청 보냄
     *      http://localhost:8080/search?q=%EA%B0%80
     * 4. server 는 client 로부터 full ASCII URL 로 표현된 요청 받음
     *      http://localhost:8080/search?q=%EA%B0%80
     * 5. server 는 % 가 붙은 경우 퍼센트 디코딩해야하는 문자로 인식 후, 디코딩한다.
     *      2 번과정의 역순으로 디코딩
     *          %EA%B0%80 -> 가
     *
     */

    public static void main(String[] args) {

        String str1 = "가나";
        String encoded1 = URLEncoder.encode(str1, StandardCharsets.UTF_8);
        System.out.println(str1 + " ---encoding---> " + encoded1); // 가나 ---encoding---> %EA%B0%80%EB%82%98
        String decoded1 = URLDecoder.decode(encoded1, StandardCharsets.UTF_8);
        System.out.println(encoded1 + " ---decoding---> " + decoded1); // %EA%B0%80%EB%82%98 ---decoding---> 가나


        String str2 = "1234";
        String encoded2 = URLEncoder.encode(str2, StandardCharsets.UTF_8);
        System.out.println(str2 + " ---encoding---> " + encoded2); // 1234 ---encoding---> 1234
        String decoded2 = URLDecoder.decode(encoded2, StandardCharsets.UTF_8);
        System.out.println(encoded2 + " ---decoding---> " + decoded2); // 1234 ---decoding---> 1234



        String str3 = "abcdefg";
        String encoded3 = URLEncoder.encode(str3, StandardCharsets.UTF_8);
        System.out.println(str3 + " ---encoding---> " + encoded3); // abcdefg ---encoding---> abcdefg
        String decoded3 = URLDecoder.decode(encoded3, StandardCharsets.UTF_8);
        System.out.println(encoded3 + " ---decoding---> " + decoded3); // abcdefg ---decoding---> abcdefg
    }
}
