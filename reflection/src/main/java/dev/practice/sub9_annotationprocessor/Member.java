package dev.practice.sub9_annotationprocessor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Member {

    private String name;
    private int age;
}
