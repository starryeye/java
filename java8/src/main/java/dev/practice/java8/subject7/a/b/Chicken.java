package dev.practice.java8.subject7.a.b;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(ChickenContainer.class) // 반복할 수 있는 어노테이션으로 만듬
public @interface Chicken {

    String value();
}
