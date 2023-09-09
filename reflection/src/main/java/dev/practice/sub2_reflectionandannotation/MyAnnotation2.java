package dev.practice.sub2_reflectionandannotation;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.FIELD}) // @Target 어노테이션으로 선언한 ElementType 으로만 적용 범위를 제한 시킬수 있다. (기본은 모두 허용이고 )
@Inherited //해당 어노테이션을 적용한 클래스를 상속받은 클래스에서도 해당 어노테이션이 조회되도록 할 수 있다. (MyBook 에서도 해당 어노테이션이 조회된다.)
public @interface MyAnnotation2 {

    /**
     * 어노테이션은 primitive 타입, primitive 타입의 레퍼런스 타입, List 타입을 선언할 수 있다.
     * 아래는 예시이다.
     */
    //public 이 생략된 것이다. default 로 기본 값을 지정해줄 수 있다. default 가 없으면 사용하는 쪽에서 반드시 지정되어야 한다.
    String name() default "annotation";

    int number() default 100;

    // 요소 명을 value 로 선언하면, 사용하는 쪽에서는 @MyAnnotation2(value = "") 로 할필요 없이, @MyAnnotation2("") 로 사용할 수 있다.
    // 하지만, 여러 요소를 함께 설정할 때는 value 도 해줘야한다. (value = "", number = 0)
    String value() default "value";
}
