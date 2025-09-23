package annotation.sub1_declaration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation1 {

    /**
     * annotation..
     *      annotation 의 영어 단어 뜻은 "주석" 이다.
     *      코드에 추가적인 정보를 주석처럼 제공할 수 있는 기술이다.
     *      일반적인 "//" 주석은 컴파일러에 의해 삭제 되지만, annotation 은 컴파일 시점이나 런타임에도 살아있으면서 메타데이터로 제공된다.
     *
     * annotation 의 기본 선언 방법에 대해 알아본다.
     *
     * annotation 은 @interface 키워드로 선언한다.
     * annotation 은 속성(element, 요소)를 가질 수 있다.
     *      ex. String value();
     *              value 속성은 String 타입을 담을 수 있다는 의미이다.
     *              interface 의 메서드와 비슷하게 생겼지만, 괄호내에 파라미터를 선언할 수 는 없다.
     *      속성의 데이터 타입.
     *          Java 의 primitive 타입은 모두 가능
     *          String 타입 가능
     *          enum 타입 가능
     *          메타데이터인 Class<T> 타입 가능
     *          위 타입들의 배열([]) 가능
     *          다른 annotation 타입 가능
     *          void 불가능
     *      예외를 선언할 수 없다.
     *
     */

    String value();

    int count() default 0;

    String[] tags() default {};

//    MyObject myObj; // compile error
//    MyInterface myInterface; // compile error

    MyEnum myEnum();

    Class<? extends MyObject> myObjectMetadata() default MyObject.class;
}
