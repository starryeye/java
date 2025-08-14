package io.input_stream_and_output_stream.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStream2 {

    public static void main(String[] args) throws IOException {

        /**
         * 아래와 같이 try-with-resources 적용가능
         */
        try (FileOutputStream fos = new FileOutputStream("temp/hello.dat")) {
            fos.write(65); // 'A'
            fos.write(66); // 'B'
            fos.write(67); // 'C'
        }

        /**
         * 아래와 같이 try-with-resources 적용가능
         */
        try (FileInputStream fis = new FileInputStream("temp/hello.dat")) {
            /**
             * 아래와 같이 while 문을 통하여
             * EOF 를 반환할 때까지 read() 를 수행하여 1byte 씩 읽을 수 있다.
             */
            int data;
            while ((data = fis.read()) != -1) {
                System.out.println(data);
            }
        }
    }
}
