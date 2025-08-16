package io.input_stream_and_output_stream.practice.member.repository;

import io.input_stream_and_output_stream.practice.member.Member;
import io.input_stream_and_output_stream.practice.member.MemberRepository;

import java.util.ArrayList;
import java.util.List;

public class MemoryMemberRepository implements MemberRepository {

    private final List<Member> members = new ArrayList<>();

    @Override
    public void add(Member member) {
        members.add(member);
    }

    @Override
    public List<Member> findAll() {
        return List.copyOf(members);
    }
}
