package io.input_stream_and_output_stream.basic_stream.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStream1 {

    /**
     * FileOutputStream / FileInputStream
     * Java 프로세스와 파일간 데이터를 주고 받아 본다.
     *
     * write(int)
     *      byte 단위로 값을 출력한다.
     *      ASCII 코드 숫자 값이 파라미터로 들어간다.
     * read()
     *      byte 단위로 값을 읽는다.
     *      ASCII 코드 숫자 값이 반환된다.
     *
     * 참고
     * 프로젝트 하위에 temp 폴더는 있어야한다. (파일은 존재하지 않아도 됨.)
     *
     * 참고.
     * temp/hello.dat 파일을 열어보면, ABC 가 보이는데..
     * Java 프로세스에서는 파일에 byte 단위로 숫자 656667 을 입력해두었지만..
     * IDE 에서 파일을 읽을 때, UTF-8(현재 시스템 기본 charset) 으로 디코딩해서 봤기 때문에 ABC 가 출력된다.
     *
     * 참고
     * Java 에서 byte 타입은 부호 있는 8bit 값(-128 ~ 127) 이다.
     * 하지만, ASCII charset 의 유효값은 0 ~ 255 이라서 write 의 파라미터와 read 반환값 타입이 int 이다.
     *      즉, 파라미터와 반환값의 유효값은 0 ~ 255 이다.
     *      read 의 경우 EOF 를 표현하기 위해 -1 도 쓰인다.
     */

    public static void main(String[] args) throws IOException {

        // Java 프로세스에서 출력
        FileOutputStream fos = new FileOutputStream("temp/hello.dat", false); // append 값은 false 가 기본값이고 기존 데이터를 다 지우고 처음 부터 쓴다는 것이다.
        fos.write(65); // ASCII charset 에서 65 는 A 이다.
        fos.write(66); // ASCII charset 에서 66 는 B 이다.
        fos.write(67); // ASCII charset 에서 67 는 C 이다.
        fos.close();

        // Java 프로세스로 입력
        FileInputStream fis = new FileInputStream("temp/hello.dat");
        System.out.println(fis.read()); // ASCII charset 에서 A 는 65 이다.
        System.out.println(fis.read()); // ASCII charset 에서 B 는 66 이다.
        System.out.println(fis.read()); // ASCII charset 에서 C 는 67 이다.
        System.out.println(fis.read()); // -1, 파일의 끝이라 더이상 읽을 수 없다면 -1 반환됨 (EOF)
        fis.close();
    }
}
