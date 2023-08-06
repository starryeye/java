package dev.practice.java8.ga.b;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {

    /**
     * 중복으로 @Chickn 어노테이션을 적용할 수 있도록 @ChickenContainer 를 만들었다.
     *
     * @Retention, @Target 은 반드시 @Chicken 보다 넓은 범위여야 한다.
     */

    Chicken[] value(); // 감싸고 있을 어노테이션의 배열을 원소로 가진다.
}
