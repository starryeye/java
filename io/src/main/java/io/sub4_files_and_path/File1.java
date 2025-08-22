package io.sub4_files_and_path;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class File1 {

    /**
     * Java 1.0 부터 제공하는 File 객체 대표적 사용법
     *
     * 인스턴스? 메서드로 기능 제공
     */

    public static void main(String[] args) throws IOException {

        File file = new File("temp/example.txt");       // 파일
        File directory = new File("temp/exampleDir");   // 디렉토리


        // File::exist(), 파일이나 디렉토리가 존재하는지 확인
        System.out.println("File::exist(): " + file.exists());


        // File::createNewFile(), 새 파일 생성
        System.out.println("File::createNewFile(): " + file.createNewFile());


        // File::mkdir(), 새 디렉토리 생성
        System.out.println("File::mkdir(): " + directory.mkdir());


        // File::delete(), 파일이나 디렉토리를 삭제
        //System.out.println("File::delete(): " + file.delete());


        // File::isFile(), 파일인지 확인
        System.out.println("File::isFile(): " + file.isFile());


        // File::isDirectory(), 디렉토리인지 확인
        System.out.println("File::isDirectory(): " + directory.isDirectory());


        // File::getName(), 파일이나 디렉토리의 이름을 반환
        System.out.println("File::getName(): " + file.getName());


        // File::length(), 파일의 크기를 바이트 단위로 반환
        System.out.println("File::length(): " + file.length() + " bytes");


        // File::renameTo(File dest), 파일의 이름을 변경하거나 이동
        File newFile = new File("temp/newExample.txt");
        System.out.println("File::renameTo(File dest): " + file.renameTo(newFile)); // temp/example.txt -> temp/newExample.txt


        // File::lastModified(), 마지막으로 수정된 시간을 반환
        System.out.println("File::lastModified(): " + new Date(newFile.lastModified()));
    }
}
