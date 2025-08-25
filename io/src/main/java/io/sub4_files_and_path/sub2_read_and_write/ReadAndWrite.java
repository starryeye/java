package io.sub4_files_and_path.sub2_read_and_write;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ReadAndWrite {

    /**
     * basic stream 인 FileInputStream/FileOutputStream 으로 직접 io 를 다루거나
     * filter stream 인 FileReader/FileWriter + BufferedReader/BufferedWriter 으로 io 를 다루기보다..
     *
     * Files 를 이용하여 파일의 문자를 편리하게 쓰고 읽어본다.
     *
     * 만약, 정밀한 buffered 메모리 기반의 읽기가 필요하다면, 기존의 방법으로 돌아가자
     * io.sub2_reader_and_writer.sub1_filter_stream.Buffered 와 비교해보자.
     */

    public static void main(String[] args) throws IOException {

        String writeString = "ABC\n가나다\n123";
        String filePath = "temp/hello2.txt";

        // 한번에 쓰고 파일의 모든 문자 한번에 읽기
        writeAllReadAll1(filePath, writeString);

        // 한번에 쓰고 파일에서 한번에 읽기(라인 리스트로 반환)
        writeAllReadAll2(filePath, writeString);

        // 한번에 쓰고 파일에서 한줄씩 읽기
        writeAllReadLine(filePath, writeString); // io.sub2_reader_and_writer.sub1_filter_stream.Buffered 와 비교해보자.
    }

    private static void writeAllReadAll1(String filePath, String writeString) throws IOException {

        System.out.println("==== [writeAllReadAll1] Write String ====");
        System.out.println(writeString);

        Path path = Path.of(filePath);

        // 한번에 Write
        Files.writeString(path, writeString, StandardCharsets.UTF_8);


        // 한번에 Read
        String readString = Files.readString(path, StandardCharsets.UTF_8);

        System.out.println("==== [writeAllReadAll1] Read String ====");
        System.out.println(readString);
    }

    private static void writeAllReadAll2(String filePath, String writeString) throws IOException {

        System.out.println("==== [writeAllReadAll2] Write String ====");
        System.out.println(writeString);

        Path path = Path.of(filePath);

        // 한번에 Write
        Files.writeString(path, writeString, StandardCharsets.UTF_8);


        // 한번에 읽기(라인 리스트로 반환)
        System.out.println("==== [writeAllReadAll2] Read String ====");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static void writeAllReadLine(String filePath, String writeString) throws IOException {
        System.out.println("==== [writeAllReadLine] Write String ====");
        System.out.println(writeString);

        Path path = Path.of(filePath);

        // 한번에 Write
        Files.writeString(path, writeString, StandardCharsets.UTF_8);


        // 한줄씩 읽기
        System.out.println("==== [writeAllReadLine] Read String ====");
        try (Stream<String> lineStream = Files.lines(path, StandardCharsets.UTF_8)) { // try with resources
            lineStream.forEach(System.out::println);
        }
    }
}
