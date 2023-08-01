package dev.practice.java8.ba;

public interface FooExt extends Foo{

    /**
     * Foo 에서는 printNameUpperCase 가 default 메서드였는데...
     * Bar 에서는 default 메서드로 제공하고 싶지 않다면..
     * 추상메서드로 재 선언하면 된다.
     */
    void printNameUpperCase();
}
