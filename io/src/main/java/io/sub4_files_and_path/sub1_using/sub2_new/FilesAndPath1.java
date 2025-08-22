package io.sub4_files_and_path.sub1_using.sub2_new;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesAndPath1 {

    /**
     * Java 1.7 부터 제공하는 Files, Path, Paths 객체 대표적 사용법
     *
     * static 메서드로 제공
     */

    public static void main(String[] args) throws IOException {

        Path file = Path.of("temp/example.txt");       // 파일
        Path directory = Path.of("temp/exampleDir");   // 디렉토리


        // Files::exist(Path), 파일이나 디렉토리가 존재하는지 확인
        System.out.println("Files::exist(Path): " + Files.exists(file));


        // Files::createFile(Path), 새 파일 생성
        try {
            System.out.println("Files::createFile(Path): " + Files.createFile(file));
        } catch (FileAlreadyExistsException e) { // runtime exception 이다.
            System.out.println(file + " File already exists..");
        }


        // Files.createDirectory(Path), 새 디렉토리 생성
        try {
            System.out.println("Files.createDirectory(Path): " + Files.createDirectory(directory));
        } catch (FileAlreadyExistsException e) { // runtime exception 이다.
            System.out.println(directory + " Directory already exists..");
        }


        // Files::delete(Path), 파일이나 디렉토리를 삭제
        //System.out.println("Files::delete(Path): " + Files.delete(file));


        // Files::isRegularFile(Path), 일반 파일인지 확인
        System.out.println("Files::isRegularFile(Path): " + Files.isRegularFile(file));


        // Files::isDirectory(Path), 디렉토리인지 확인
        System.out.println("Files::isDirectory(Path): " + Files.isDirectory(directory));


        // Path::getFileName(), 파일이나 디렉토리의 이름을 반환
        System.out.println("Path::getFileName(): " + file.getFileName());


        // Files::size(Path), 파일의 크기를 바이트 단위로 반환
        System.out.println("Files::size(Path): " + Files.size(file) + " bytes");


        // Files.move(source, target, CopyOption), 파일의 이름을 변경하거나 이동
        Path newFile = Paths.get("temp/newExample.txt");
        System.out.println("Files.move(source, target, CopyOption): " + Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING)); // temp/example.txt -> temp/newExample.txt


        // Files::getLastModifiedTime(Path), 마지막으로 수정된 시간을 반환
        System.out.println("Files::getLastModifiedTime(Path): " + Files.getLastModifiedTime(newFile));


        // Files::readAttributes(), 파일의 기본 정보를 반환
        BasicFileAttributes basicFileAttributes = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("===== Attributes =====");
        System.out.println("BasicFileAttributes::creationTime(): " + basicFileAttributes.creationTime());
        System.out.println("BasicFileAttributes::isDirectory(): " + basicFileAttributes.isDirectory());
        System.out.println("BasicFileAttributes::isRegularFile(): " + basicFileAttributes.isRegularFile());
        System.out.println("BasicFileAttributes::isSymbolicLink(): " + basicFileAttributes.isSymbolicLink());
        System.out.println("BasicFileAttributes::size(): " + basicFileAttributes.size());
    }
}
