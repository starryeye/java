package io.input_stream_and_output_stream.practice.member;

import java.util.List;

public interface MemberRepository {
    void add(Member member);

    List<Member> findAll();
}
