package io.sub3_practice.member.repository;

import io.sub3_practice.member.Member;
import io.sub3_practice.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileMemberRepository implements MemberRepository {

    /**
     * FileWriter / FileReader (내부에 FileOutputStream / FileInputStream 존재) 와
     * BufferedWriter / BufferedReader 를 이용하여 파일에 Member 데이터를 저장한다.
     *      한줄(라인) 단위로 Member 데이터 단위를 구분하고 DELIMITER 로 구분하여 Member 속성을 구분한다.
     *
     */

    private static final String FILE_PATH = "temp/members-txt.dat";
    private static final String DELIMITER = ",";

    @Override
    public void add(Member member) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, UTF_8, true))) {
            bw.write(member.getId() + DELIMITER + member.getName() + DELIMITER + member.getAge());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH, UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] memberData = line.split(DELIMITER);
                members.add(new Member(memberData[0], memberData[1], Integer.valueOf(memberData[2])));
            }
            return members;
        } catch (FileNotFoundException e) {
            return List.of();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
