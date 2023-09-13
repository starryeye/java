package dev.practice.sub9_annotationprocessor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void getterSetter() {
        Member member = new Member();
        member.setAge(10);

        assertThat(member.getAge()).isEqualTo(10);
    }

}