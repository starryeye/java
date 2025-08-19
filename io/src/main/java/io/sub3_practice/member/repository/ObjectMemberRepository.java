package io.sub3_practice.member.repository;

import io.sub3_practice.member.Member;
import io.sub3_practice.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectMemberRepository implements MemberRepository {

    /**
     * Java 의 Serialization / Deserialization (직렬화/역직렬화)
     * 직렬화는 메모리에 존재하는 객체 인스턴스를 byte stream 으로 변환하는 것을 의미한다. (이후 파일에 저장하거나 네트워크 전송에 사용)
     * 역직렬화는 직렬화의 반대이다.
     *
     * Serializable 인터페이스(마커 인터페이스, 표시가 목적 추상 메서드가 없음) 를 구현하면 직렬화/역직렬화에 사용될 수 있는 객체가 된다.
     *      예시에 보면, 직렬화 대상 인스턴스는 ArrayList<Member> 타입이다.
     *          ArrayList 는 Serializable 을 구현하고, Member 객체도 Serializable 을 구현하도록 함
     *
     * 객체 직렬화/역직렬화를 위한 input/output stream
     *      ObjectInputStream / ObjectOutputStream (filter stream 이다.)
     *
     * 요즘은 객체 직렬화를 사용하지 않음
     *      1. 직렬화 대상 클래스의 구조가 바뀌면(필드만 바껴도..) 이전 파일간의 호환성 문제 생김
     *      2. Java 끼리만 쉬움.. 다른 언어 및 시스템과는 어려움
     *      3. 느리고 리소스 많이 씀
     *      4. 직렬화 된 데이터가 큼
     *      대안..
     *          JSON, Protobuf, Avro
     *          xml 은 느리고 무겁고 복잡해서 사용 X
     */

    private static final String FILE_PATH = "temp/members-obj.dat";

    @Override
    public void add(Member member) {

        List<Member> members = findAll();

        members.add(member);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) { // try with resources
            oos.writeObject(members); // 객체를 byte stream 으로 직렬화 하여 쓰기
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) { // try with resources
            Object findObject = ois.readObject(); // byte stream 을 객체로 역직렬화 하여 읽기
            return (List<Member>) findObject;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
