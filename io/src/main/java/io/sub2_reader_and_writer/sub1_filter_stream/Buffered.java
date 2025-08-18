package io.sub2_reader_and_writer.sub1_filter_stream;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Buffered {

    /**
     * BufferedWriter / BufferedReader 는 Writer / Reader 를 상속하는 filter stream 이며..
     *      특정 버퍼 사이즈 단위로 IO 가 이루어지도록 하는 버퍼 기능이 제공되고
     *      문자열을 한줄(라인) 단위로 다룰 수 있는 메서드도 제공한다.
     */

    private static final int BUFFER_SIZE = 8192;
    private static final String FILE_NAME = "temp/hello.txt";

    public static void main(String[] args) throws IOException {

        String writeString = "ABC\n가나다\n123";


        System.out.println("== Write String ==");
        System.out.println(writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8); // FileOutputStream basic stream 을 가지는 filter stream
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE); // filter stream
        bw.write(writeString); // 버퍼사이즈에 도달하지 않으면 write 되지 않음
        bw.close(); // 버퍼사이즈에 도달하지 않았음에도 close 하면 write 후 close 함

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8); // FileInputStream basic stream 을 가지는 filter stream
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE); // filter stream

        String line;
        while ((line = br.readLine()) != null) { // BufferedReader::readLine, 한줄 단위로 read 하도록 지원됨
            content.append(line).append("\n");
        }
        br.close();

        System.out.println("== Read String ==");
        System.out.println(content);
    }
}
