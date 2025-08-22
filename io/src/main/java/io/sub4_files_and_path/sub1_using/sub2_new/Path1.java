package io.sub4_files_and_path.sub1_using.sub2_new;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Path1 {

    /**
     * Java 1.7 에서는 파일이나 디렉토리 경로 개념에 대한 객체로 Path 를 사용한다.
     */

    public static void main(String[] args) throws IOException {
        Path path = Path.of("temp/..");
        System.out.println("path = " + path);

        // 절대 경로
        System.out.println("Absolute path = " + path.toAbsolutePath());
        // 정규 경로
        System.out.println("Canonical path = " + path.toRealPath());


        // try with resources, Files.list(Path) 는 내부적으로 DirectoryStream 을 열어서 닫아줘야한다.
        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(p -> {
                String type = switch (p) {
                    case Path pathType when Files.isSymbolicLink(pathType) -> "L"; // 심볼릭 링크
                    case Path pathType when Files.isRegularFile(pathType, LinkOption.NOFOLLOW_LINKS) -> "F"; // 일반 파일
                    case Path pathType when Files.isDirectory(pathType, LinkOption.NOFOLLOW_LINKS) -> "D"; // 디렉토리
                    default -> "O"; // 그 외 (소켓, 디바이스 등)
                };

                System.out.println(type + " | " +  p.getFileName());
            });
        }
    }
}
