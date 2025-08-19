package io.sub3_practice.member.repository;

import io.sub3_practice.member.Member;
import io.sub3_practice.member.MemberRepository;

import java.io.*;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ObjectMemberRepository2 implements MemberRepository {

    private static final Path FILE_PATH = Path.of("temp/members-obj.dat");

    @Override
    public synchronized void add(Member member) { // 동기화 락

        // findAll() 의 반환이 null, empty List, 불변 list 일 수 도 있다고 가정해봄..
        // findAll() 이 null 이면 orElseGet, 그외면 ArrayList 생성자 호출(불변이라도 가변으로 변경)
        List<Member> members = Optional.ofNullable(findAll())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);

        members.add(member);

        // 원자적 저장: 임시 파일에 먼저 쓰고, 완료되면 교체
        writeMembersAtomically(members);
    }


    @Override
    public List<Member> findAll() {

        if (!Files.exists(FILE_PATH)) {
            return List.of();
        }

        try (var ois = new ObjectInputStream(Files.newInputStream(FILE_PATH))) {
            Object obj = ois.readObject();
            return (List<Member>) obj;
        } catch (FileNotFoundException e) {
            return List.of();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeMembersAtomically(List<Member> members) {

        try {
            Files.createDirectories(FILE_PATH.getParent());

            // 임시 파일 생성
            Path tmp = Files.createTempFile(FILE_PATH.getParent(), "members-obj", ".tmp");

            // 임시 파일에 먼저 씀
            try (var oos = new ObjectOutputStream(Files.newOutputStream(tmp))) {
                oos.writeObject(members);
                oos.flush();
            }

            // 원자적으로 교체 (플랫폼에 따라 ATOMIC_MOVE 미지원일 수 있어 Fallback 추가)
            try {
                Files.move(tmp, FILE_PATH, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException ignore) {
                Files.move(tmp, FILE_PATH, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
