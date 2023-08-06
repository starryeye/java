package dev.practice.java8.ga.a;

import java.util.List;

@Chicken
public class App {

    public static void main(@Chicken String[] args) throws @Chicken RuntimeException{

        List<@Chicken String> names = List.of("Java");
    }

    /**
     * <> 내부에 있는 걸 "타입 파라미터"라 한다.
     *
     * 나머지 @Chicken 을 붙인 곳은 모두 "타입"이다.
     */
    @Chicken
    static class FeelsLikeChicken<@Chicken T> {

        public static <@Chicken C> void print(@Chicken C c) {

        }
    }
}
