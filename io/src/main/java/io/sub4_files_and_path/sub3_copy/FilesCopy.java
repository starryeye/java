package io.sub4_files_and_path.sub3_copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FilesCopy {

    /**
     * 파일을 복사하는 여러가지 방법에 대해 알아본다.
     *
     * 파일 내부의 내용을 읽어서 처리해야하거나, 스트림을 통해 네트워크에 파일 내용을 전송해야한다면
     *      InputStream / OutputStream 을 직접 이용해야한다.
     * 하지만, 단순하게 파일을 복사해야한다면, Files::copy 를 이용하자.
     *
     * 항상 Files 의 메서드를 먼저 찾아보고 없으면 Stream 을 이용해서 구현하도록 하자..
     */

    public static void main(String[] args) throws IOException {

        String fileName = "temp/copy.dat";
        int fileSize = 200 * 1024 * 1024; // 200MB

        // create
        createFile(fileName, fileSize);

        String source = fileName;
        String target = "temp/copy_new.dat";

        // copy
//        fileCopy1(source, target);
//        fileCopy2(source, target);
        fileCopy3(source, target);
    }

    private static void createFile(String fileName, int fileSize) throws IOException {
        long startTime = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] buffer = new byte[fileSize]; // element 는 0 으로 초기화됨
        fos.write(buffer);
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[createFile] " + fileName + " file created, size: " + (fileSize / 1024 / 1024) + "MB, time: " + (endTime - startTime));
    }

    private static void fileCopy1(String source, String target) throws IOException {
        /**
         * FileInputStream 에서 readAllBytes 를 통해 한번에 모든 데이터를 읽는다.
         * FileOutputStream 에서 write(byte[]) 를 통해 한번에 모든 데이터를 저장한다.
         */
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);

        // copy
        byte[] bytes = fis.readAllBytes();
        fos.write(bytes);

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[fileCopy1] " + target + " file copied, time: " + (endTime - startTime));
    }

    private static void fileCopy2(String source, String target) throws IOException {
        /**
         * FileInputStream::transferTo() 를 이용한다. (Java 9)
         * InputStream 에서 읽은 데이터를 바로 OutputStream 으로 출력한다. (file to network 도 가능할듯..)
         * fileCopy1 과 비교하여 조금의 최적화 정도의 차이일듯..
         */
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);

        // copy
        fis.transferTo(fos);

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("[fileCopy2] " + target + " file copied, time: " + (endTime - startTime));
    }

    private static void fileCopy3(String source, String target) throws IOException {
        /**
         * Files.copy() 는 Java 프로세스에 파일 데이터를 불러오지 않고, 운영체제의 파일 복사 기능을 사용한다.
         */
        long startTime = System.currentTimeMillis();

        Path sourcePath = Path.of(source);
        Path targetPath = Path.of(target);

        // copy, StandardCopyOption.REPLACE_EXISTING = 덮어쓰기옵션
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        long endTime = System.currentTimeMillis();
        System.out.println("[fileCopy3] " + target + " file copied, time: " + (endTime - startTime));
    }
}
