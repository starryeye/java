package dev.practice.sub2_reflectionandannotation;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        /**
         * 리플랙션을 이용해 아래와 같이
         * Book 의 Class 타입 인스턴스에서 getAnnotations 메서드를 통해
         * MyAnnotation 을 가져오려고 시도하면, 조회가 안된다.
         * 반면, MyAnnotation 은 가져와진다.
         */
        Arrays.stream(Book.class.getAnnotations())
                .forEach(System.out::println);

        /**
         * MyBook 은 Book 을 상속하였고 Book 에 적용된 어노테이션 중 @MyAnnotation2 에 @Inherited 가 적용되어 있기 때문에
         * 조회된다. (@Inherited 가 적용되어있지 않다면 아래에서 조회가 되지 않는다.)
         */
        Arrays.stream(MyBook.class.getAnnotations())
                .forEach(System.out::println);

        // getDeclaredAnnotations() 로 조회하면 실제로 MyBook 에 적용한 어노테이션만 조회한다. (@MyAnnotation2 조회 안됨)
        Arrays.stream(MyBook.class.getDeclaredAnnotations())
                .forEach(System.out::println);

        /**
         * 어노테이션의 값을 참조할 수 있다.
         */
        Arrays.stream(Book.class.getFields())
                .forEach(field -> {
                    Arrays.stream(field.getAnnotations())
                            .forEach(annotation -> {
                                if(annotation instanceof MyAnnotation2) { // MyAnnotation2 가 붙은 필드를 찾는 의미도 된다.
                                    System.out.println(((MyAnnotation2) annotation).name());
                                    System.out.println(((MyAnnotation2) annotation).name());
                                    System.out.println(((MyAnnotation2) annotation).value());
                                }
                            });
                });
    }
}
