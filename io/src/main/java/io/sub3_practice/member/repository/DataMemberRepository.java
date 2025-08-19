package io.sub3_practice.member.repository;

import io.sub3_practice.member.Member;
import io.sub3_practice.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataMemberRepository implements MemberRepository {

    /**
     * DataOutputStream / DataInputStream 을 이용하여 Member 데이터를 저장하고 읽는다.
     * Java 숫자타입(Integer, Double, Boolean 등)의 할당 byte 값 그대로 파일에 저장하고 읽을 수 있다.
     *      sub1_input_stream_and_output_stream.sub2_filter_stream.sub2_data 참고
     *
     * writeUTF, writeInt, readUTF, readInt 등을 이용하면서 몇 byte 만큼 읽고 쓸지를 알기 때문에 구분자가 필요없음
     *      필드간 구분이나 Member 데이터간 구분이 필요없음
     */

    private static final String FILE_PATH = "temp/members-data.dat";

    @Override
    public void add(Member member) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH, true))) { // try with resources
            dos.writeUTF(member.getId());
            dos.writeUTF(member.getName());
            dos.writeInt(member.getAge());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH))) { // try with resources
            while (dis.available() > 0) {
                Member member = new Member(dis.readUTF(), dis.readUTF(), dis.readInt());
                members.add(member);
            }
            return members;
        } catch (FileNotFoundException e) {
            return List.of();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
