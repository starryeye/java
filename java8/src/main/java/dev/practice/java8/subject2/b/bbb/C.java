package dev.practice.java8.subject2.b.bbb;

public class C implements A{

    /**
     * Java 8 이후 부터는 interface 에 default 메서드가 추가되어
     * interface 와 class 사이에 추상메서드 구현 강제를 피하기 위한 방법으로
     * abstract class 가 필요 없어졌다.
     *
     * 대표적인 케이스로..
     * Spring 의 WebMvcConfigurer interface 가 있다.
     * -> 기존에는 WebMvcConfigurerAdapter 가 abstract class 로 있었다. (spring 5 에서 deprecated 였고, spring 6 에서 사라짐)
     */
}
