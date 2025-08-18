package io.sub1_input_stream_and_output_stream.sub1_basic_stream.sub1_file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileStream4 {

    /**
     * readAllBytes()
     *      한번의 호출로 모든 데이터를 입력한다.
     *      주의, 큰 파일의 경우 OutOfMemoryError 발생가능
     */

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input);
        fos.close(); // try-with-resources 를 사용하지 않으면 반드시 close 호출

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] readBytes = fis.readAllBytes();
        System.out.println(Arrays.toString(readBytes));
        fis.close(); // try-with-resources 를 사용하지 않으면 반드시 close 호출
    }
}
