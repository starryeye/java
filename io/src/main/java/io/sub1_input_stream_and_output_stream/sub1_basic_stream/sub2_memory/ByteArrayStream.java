package io.sub1_input_stream_and_output_stream.sub1_basic_stream.sub2_memory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayStream {
    /**
     * ByteArrayOutputStream / ByteArrayInputStream
     * Java 프로세스에서 외부 메모리에 데이터를 저장할 수 있다.
     *
     * 메모리에 데이터를 저장하고 읽을 때는 Collection 이나 배열을 사용하면 더 편하기 때문에 사용되지 않는다.
     */

    public static void main(String[] args) throws IOException {
        byte[] input = {1, 2, 3};

        // 메모리에 쓰기
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(input);

        // 메모리에서 읽기
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        byte[] bytes = bais.readAllBytes();
        System.out.println(Arrays.toString(bytes));
    }
}
