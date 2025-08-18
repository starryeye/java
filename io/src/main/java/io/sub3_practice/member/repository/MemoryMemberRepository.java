package io.sub3_practice.member.repository;

import io.sub3_practice.member.Member;
import io.sub3_practice.member.MemberRepository;

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
