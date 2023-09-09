package dev.practice.sub2_reflectionandannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation 은 주석과 같은 취급을 받는다. (기능이 더 있긴하다.)
 *
 * 주석은
 * 소스 코드 (.class 의 바이트코드) 까지는 존재하지만,
 * 메모리상에는 주석은 제외하고 로딩된다.
 *
 * 메모리상에도 해당 정보를 로딩시키려면..
 * @Retention 어노테이션의 value 요소로 Runtime 정책을 적용시켜줘야한다. (기본 값은 class 이다.)
 * -> MyAnnotation2
 */
@Retention(value = RetentionPolicy.CLASS)
public @interface MyAnnotation {
}
