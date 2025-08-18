package io.sub2_reader_and_writer.sub1_filter_stream;

import java.io.*;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class StreamReaderAndStreamWriter {

    /**
     * OutputStreamWriter / InputStreamReader 는 대표적 filter stream 으로..
     * 다양한 basic stream 을 넣고 쓸 수 있다. (여기 예제에서는 file 과 데이터 전송이 가능한 FileOutputStream / FileInputStream 을 사용)
     *
     * OutputStreamWriter / InputStreamReader 의 의의에 대해 알아본다.
     *
     * readAndWriteByBasicStream()
     *      파일에 인코딩 되기전 문자를 쓰고 읽기 위해서 basic stream(FileOutputStream / FileInputStream) 을 이용하면
     *          output 에서는 직접 인코딩해주고 byte 로 전달해야하고, input 에서는 직접 byte 를 디코딩하여 문자로 변환시켜야한다.
     * readAndWriteByFilterStream1()
     *      filter stream(OutputStreamWriter / InputStreamReader) 을 이용하면
     *          인코딩 없이 문자를 그대로 전달하고 전달받아 편하게 사용이 가능하다.
     *          물론, 내부에는 basic stream(FileOutputStream / FileInputStream)이 그대로 이용되며
     *          개발자를 대신하여 설정한 charset 으로 인코딩/디코딩 작업을 대신 해주고 있다.
     * readAndWriteByFilterStream2()
     *      filter stream(FileWriter / FileReader) 를 이용하면
     *          basic stream(FileOutputStream / FileInputStream) 를 직접 생성해서
     *              filter stream(FileWriter / FileReader) 에 전달할 필요가 없다. (내부에서 직접 생성해준다.)
     *          (OutputStreamWriter / InputStreamReader) , (FileOutputStream / FileInputStream) 을 이용하는 것은 동일함..
     *
     */

    private static final String FILE_NAME = "temp/hello.txt";

    public static void main(String[] args) throws IOException {

        String text = "ABC"; // 이 text 를 파일에 쓰고 읽는다.
//        String text = "123"; // 숫자 처럼 보이지만 이 것도 문자(그림)이다. (charset 으로 인코딩/디코딩이 필요함)

        readAndWriteByBasicStream(text);        // FileOutputStream / FileInputStream 을 직접 이용해서 문자열을 파일에 쓰고 읽어본다.
        readAndWriteByFilterStream1(text);      // OutputStreamWriter / InputStreamReader 를 이용해서 문자열을 파일에 쓰고 읽어본다.
        readAndWriteByFilterStream2(text);      // FileWriter / FileReader 를 이용해서 문자열을 파일에 쓰고 읽어본다.

    }

    private static void readAndWriteByBasicStream(String text) throws IOException {

        // 문자 - byte UTF-8 인코딩
        byte[] writeBytes = text.getBytes(UTF_8);
        System.out.println("[FileOutputStream] write String: " + text);
        System.out.println("[FileOutputStream] write bytes: " + Arrays.toString(writeBytes));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeBytes);
        fos.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readBytes = fis.readAllBytes();
        fis.close();

        // byte -> String UTF-8 디코딩
        String readString = new String(readBytes, UTF_8);

        System.out.println("[FileInputStream] read bytes: " + Arrays.toString(readBytes));
        System.out.println("[FileInputStream] read String: " + readString);
    }

    private static void readAndWriteByFilterStream1(String text) throws IOException {

        System.out.println("[OutputStreamWriter] write String: " + text);

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);

        osw.write(text); // 인코딩 값(byte)이 아닌 String
        osw.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, UTF_8);

        /**
         * InputStreamReader::read 의 반환 값은 int type 인데..
         * 사실 char 이라 생각해야한다. (char 는 -1 을 표현하지 못해서 int 로 반환되는 것 뿐이며 char 로 형 변환해줘야함)
         */
        StringBuilder content = new StringBuilder();
        int ch;
        while ((ch = isr.read()) != -1) {
            content.append((char) ch);
        }
        isr.close();

        System.out.println("[InputStreamReader] read String: " + content);
    }

    private static void readAndWriteByFilterStream2(String text) throws IOException {
        System.out.println("[FileWriter] write String: " + text);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8); // basic stream 을 직접 생성해서 파라미터로 전달해줄 필요가 없어짐
        fw.write(text);
        fw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8); // basic stream 을 직접 생성해서 파라미터로 전달해줄 필요가 없어짐
        int ch;
        while ((ch = fr.read()) != -1) {
            content.append((char) ch);
        }
        fr.close();

        System.out.println("[FileReader] read String: " + content);
    }
}
