package io.sub1_input_stream_and_output_stream.sub2_filter_stream.sub3_console;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SystemOut {

    /**
     * console 출력에 자주 사용되는 System.out 은 사실 PrintStream 객체이며
     * PrintStream 은 OutputStream 및 FilterOutputStream 을 상속한다.
     *
     * try-with-resources나 close()를 안 한 이유
     *      라이프사이클 관리 주체가 JVM 이며..
     *      일반적인 파일 스트림(FileOutputStream)과 달리, System.out 은 JVM 이 종료 시점에 OS 리소스를 해제한다.
     *      따라서 중간에 명시적으로 닫을 필요가 없음
     */

    public static void main(String[] args) throws IOException {

        ex1(); // System.out.println() 은 PrintStream 이며 OutputStream 을 이용한 것
        ex2(); // PrintStream 은 filter stream 인 FilterOutputStream 이다.
    }

    private static void ex1() throws IOException {
        PrintStream printStream = System.out;

        printStream.println("Hello, world!"); // System.out.println() 은 내부에서 아래코드처럼 구성되어있다. (최적화 빼고..)

        byte[] bytes = "Hello, world!\n".getBytes(UTF_8);
        printStream.write(bytes);
    }

    private static void ex2() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("temp/print.txt"); // basic stream
        PrintStream printStream = new PrintStream(fos); // filter stream

        printStream.println("hello java!");
        printStream.println(10);
        printStream.println(true);
        printStream.printf("hello %s", "world");
        printStream.close();
    }
}
