package io.input_stream_and_output_stream.filter_stream.buffered;

import java.io.*;

public class BufferedStream {

    /**
     * 성능 측정을 직접해봄으로써
     * BufferedInputStream / BufferedOutputStream 의 의의에 대해 알아본다.
     *
     * writeFileByFileStream() / readFileByFileStream()
     *      FileInputStream / FileOutputStream 을 직접 이용
     *      read/write 1회에 1byte 씩 다루어 10,000,000 회의 I/O(디스크와 통신) 가 각각 발생한다.
     *          OS 로 10,000,000 회의 system call 이 발생되어 상당한 오버헤드를 가진다.
     *
     * BufferedWriteFileByFileStream() / BufferedReadFileByFileStream()
     *      FileInputStream / FileOutputStream 을 직접 이용
     *      read/write 1회에 BUFFER_SIZE(8KB) 씩 다루어 (10,000,000/8,192) 회의 I/O(디스크와 통신) 가 각각 발생한다.
     *
     * bufferedWriteFileByBufferedStream() / bufferedReadFileByBufferedStream
     *      FileInputStream / FileOutputStream 을 wrapping 한 BufferedOutputStream / BufferedInputStream 을 사용한다. (Java 제공 객체)
     *          BufferedWriteFileByFileStream() / BufferedReadFileByFileStream() 처럼 직접 buffer 를 생성하고 관리해줄 필요가 없다.
     *      read/write 1회에 BUFFER_SIZE(8KB) 씩 다루어 (10,000,000/8,192) 회의 I/O(디스크와 통신) 가 각각 발생한다.
     *      동기화 락이 적용되어서 thread safe 하다. (참고, 동기화 락이 없는 버전은 Java 에서 제공되지 않음..)
     *
     *
     * 참고
     * 디스크나 파일 시스템에서 데이터를 읽고 쓰는 기본 단위가 4KB(4096byte), 8KB(8192byte) 이므로
     * BUFFER_SIZE 를 4KB, 8KB 로 맞추는게 가장 효율이 좋다.
     */

    private static final String FILE_NAME = "temp/buffered.dat";
    private static final int FILE_SIZE = 10 * 1024 * 1024; // 10MB (1MB = 1000KB, 1KB = 1000Byte)
    private static final int BUFFER_SIZE = 8192; // 입출력 한번당 다룰 데이터 사이즈

    public static void main(String[] args) throws IOException {

        writeFileByFileStream();                // write 1회에 1byte 씩 출력하여 10MB 파일을 생성, 13.7초
        readFileByFileStream();                 // read 1회에 1byte 씩 입력하여 10MB 파일을 읽음, 4.7초

        bufferedWriteFileByFileStream();        // write 1회에 BUFFER_SIZE 씩 출력하여 10MB 파일을 생성, 0.014초
        bufferedReadFileByFileStream();         // read 1회에 BUFFER_SIZE 씩 입력하여 10MB 파일을 읽음, 0.001초

        bufferedWriteFileByBufferedStream();    // write 1회에 BUFFER_SIZE 씩 출력하여 10MB 파일을 생성, 0.059초 (동기화 락 사용됨)
        bufferedReadFileByBufferedStream();     // read 1회에 BUFFER_SIZE 씩 입력하여 10MB 파일을 읽음, 0.053초 (동기화 락 사용됨)
    }


    private static void writeFileByFileStream() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) {
            fos.write(65); // A
        }
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[1byte/1write] " + FILE_SIZE / 1024 / 1024 + "MB " + "File created : " + FILE_NAME + " in " + (endTime - startTime) + " ms");
    }

    private static void readFileByFileStream() throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        int fileSize = 0;
        int data;
        while ((data = fis.read()) != -1) {
            fileSize += 1;
        }
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[1byte/1read] " + FILE_SIZE / 1024 / 1024 + "MB " + "File read : " + FILE_NAME + " in " + (endTime - startTime) + " ms");
    }

    private static void bufferedWriteFileByFileStream() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bufferIndex = 0;
        for (int i = 0; i < FILE_SIZE; i++) {
            buffer[bufferIndex++] = 65;

            if (bufferIndex == BUFFER_SIZE) { // 버퍼가 가득차면 한번에 write
                fos.write(buffer);
                bufferIndex = 0;
            }
        }
        if (bufferIndex > 0) { // 나머지 처리
            fos.write(buffer, 0 , bufferIndex);
        }
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[" + BUFFER_SIZE + "byte/1write] " + FILE_SIZE / 1024 / 1024 + "MB " + "File write : " + FILE_NAME + " in " + (endTime - startTime) + " ms");
    }

    private static void bufferedReadFileByFileStream() throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];
        int fileSize = 0;
        int size = 0;
        while ((size = fis.read(buffer)) != -1) {
            fileSize += size;
        }
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[" + BUFFER_SIZE + "byte/1read] " + FILE_SIZE / 1024 / 1024 + "MB " + "File read : " + FILE_NAME + " in " + (endTime - startTime) + " ms");
    }

    private static void bufferedWriteFileByBufferedStream() throws IOException {

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE); // FileOutputStream wrapped
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) {
            bos.write(65);
        }
        bos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[" + BUFFER_SIZE + "byte/1write] " + FILE_SIZE / 1024 / 1024 + "MB " + "File write : " + FILE_NAME + " in " + (endTime - startTime) + " ms, using BufferedOutputStream");
    }

    private static void bufferedReadFileByBufferedStream() throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE); // FileInputStream wrapped
        long startTime = System.currentTimeMillis();

        int fileSize = 0;
        int data = 0;
        while ((data = bis.read()) != -1) {
            fileSize++;
        }
        bis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[" + BUFFER_SIZE + "byte/1read] " + FILE_SIZE / 1024 / 1024 + "MB " + "File read : " + FILE_NAME + " in " + (endTime - startTime) + " ms, using BufferedInputStream");
    }
}
