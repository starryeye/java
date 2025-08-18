package io.sub1_input_stream_and_output_stream.sub1_basic_stream.sub3_console;

import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SystemIn {

    /**
     * System.in 도 System.out 처럼 InputStream 추상 객체를 사용한다.
     *
     * try-with-resources나 close()를 안 한 이유
     *      라이프사이클 관리 주체가 JVM 이며..
     *      일반적인 파일 스트림(FileOutputStream)과 달리, System.out 은 JVM 이 종료 시점에 OS 리소스를 해제한다.
     *      따라서 중간에 명시적으로 닫을 필요가 없음
     *
     * 참고
     * 원래 콘솔에서 입력을 받을 땐, new Scanner(System.in); 를 이용함
     */

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;

        System.out.println("입력 후 Enter를 누르세요 (예: Hello!) :");

        byte[] buffer = new byte[100];
        int bytesRead = inputStream.read(buffer); // 바이트 배열로 읽기
        System.out.println("읽은 바이트 수 = " + bytesRead);

        // 읽은 내용을 UTF-8 문자열로 변환
        String input = new String(buffer, 0, bytesRead, UTF_8);
        System.out.println("읽은 내용: " + input);
    }
}
