package dev.practice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 소스 레벨에서 해당 어노테이션을 읽어서 annotation processor 가 처리하고
 * 특정한 소스파일을 추가로 만든다.
 * 이후, 컴파일러가 소스파일을 컴파일하고 새로운 클래스인 MagicMoja 를 만들어준다.
 */
@Target(ElementType.TYPE) //interface, class, enum 에 붙일 수 있다.
@Retention(RetentionPolicy.SOURCE) // 해당 어노테이션은 컴파일 타임에만 필요하고 바이트 코드에는 필요가 없다.
public @interface Magic {
}
