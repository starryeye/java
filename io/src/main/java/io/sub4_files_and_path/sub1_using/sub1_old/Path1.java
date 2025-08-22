package io.sub4_files_and_path.sub1_using.sub1_old;

import java.io.File;
import java.io.IOException;

public class Path1 {

    /**
     * Java 1.0 에서는 파일이나 디렉토리 경로 개념에 대한 객체가 존재 하지 않았다.
     * 단순 String
     */

    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("path = " + file.getPath());

        // 절대 경로
        System.out.println("Absolute path = " + file.getAbsolutePath());
        // 정규 경로
        System.out.println("Canonical path = " + file.getCanonicalPath());

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println( (f.isFile() ? "F" : "D")  + " | " + f.getName());
        }
    }
}
