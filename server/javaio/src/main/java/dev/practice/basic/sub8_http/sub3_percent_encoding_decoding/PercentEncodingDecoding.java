package dev.practice.basic.sub8_http.sub3_percent_encoding_decoding;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PercentEncodingDecoding {

    public static void main(String[] args) {

        String str1 = "가나";
        String encoded1 = URLEncoder.encode(str1, StandardCharsets.UTF_8);
        System.out.println(str1 + " ---encoding---> " + encoded1);
        String decoded1 = URLDecoder.decode(encoded1, StandardCharsets.UTF_8);
        System.out.println(encoded1 + " ---decoding---> " + decoded1);


        String str2 = "1234";
        String encoded2 = URLEncoder.encode(str2, StandardCharsets.UTF_8);
        System.out.println(str2 + " ---encoding---> " + encoded2);
        String decoded2 = URLDecoder.decode(encoded2, StandardCharsets.UTF_8);
        System.out.println(encoded2 + " ---decoding---> " + decoded2);



        String str3 = "abcdefg";
        String encoded3 = URLEncoder.encode(str3, StandardCharsets.UTF_8);
        System.out.println(str3 + " ---encoding---> " + encoded3);
        String decoded3 = URLDecoder.decode(encoded3, StandardCharsets.UTF_8);
        System.out.println(encoded3 + " ---decoding---> " + decoded3);
    }
}
