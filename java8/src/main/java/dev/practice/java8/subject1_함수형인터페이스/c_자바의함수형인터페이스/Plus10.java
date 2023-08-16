package dev.practice.java8.subject1_함수형인터페이스.c_자바의함수형인터페이스;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
