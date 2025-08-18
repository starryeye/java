package io.sub3_practice.member;

import java.util.List;

public interface MemberRepository {
    void add(Member member);

    List<Member> findAll();
}
