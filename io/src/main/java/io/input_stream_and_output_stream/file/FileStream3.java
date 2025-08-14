package io.input_stream_and_output_stream.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileStream3 {

    /**
     * write(byte[]) / read(byte[], int, int) 를 사용하여..
     * byte 하나씩 입출력하지 않고 배열을 이용해서 한번에 입출력해본다.
     *
     * read(byte[] b, int off, int len)
     *      b : 파일에서 읽어온 바이트들이 이 배열에 저장
     *      off : b 배열 내에서 데이터를 저장하기 시작할 위치(인덱스)
     *      len : 최대 읽을 바이트 수, 파일의 남은 데이터가 len보다 작으면 그만큼만 읽는다.
     *      참고
     *          off + len > b.length() 이면, len 만큼 데이터를 읽었을 때 b 배열의 길이를 벗어나기 때문에 IndexOutOfBoundsException 발생함
     *
     * read(byte[])
     *      off 가 0 이고 len 이 byte[].length 로 동작
     *
     */

    public static void main(String[] args) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("temp/hello.dat")) {
            byte[] input = {65, 66, 67};
            fos.write(input);
        }

        try (FileInputStream fis = new FileInputStream("temp/hello.dat")) {
            byte[] buffer = new byte[10];
            int readCount = fis.read(buffer, 0, 10);
            System.out.println("readCount = " + readCount);
            System.out.println(Arrays.toString(buffer));
        }
    }
}
