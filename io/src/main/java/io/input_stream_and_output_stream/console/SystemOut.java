package io.input_stream_and_output_stream.console;

import java.io.IOException;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SystemOut {

    /**
     * console 출력에 자주 사용되는 System.out 은 사실 PrintStream 객체이며
     * PrintStream 은 OutputStream 을 상속한다.
     *
     * try-with-resources나 close()를 안 한 이유
     *      라이프사이클 관리 주체가 JVM 이며..
     *      일반적인 파일 스트림(FileOutputStream)과 달리, System.out 은 JVM 이 종료 시점에 OS 리소스를 해제한다.
     *      따라서 중간에 명시적으로 닫을 필요가 없음
     */

    public static void main(String[] args) throws IOException {

        PrintStream printStream = System.out;

        printStream.println("Hello, world!"); // System.out.println() 은 내부에서 아래코드처럼 구성되어있다. (최적화 빼고..)

        byte[] bytes = "Hello, world!\n".getBytes(UTF_8);
        printStream.write(bytes);
    }
}
