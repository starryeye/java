package annotation.sub3_annotation_inheritance;

public interface Description {

    /**
     * Java 의 모든 annotation 은 java.lang.annotation.Annotation interface 를 묵시적 상속한다.
     * -> @interface 키워드로 annotation 를 선언하면 Java 컴파일러가 자동으로 Annotation interface 를 사용하여 구현
     *
     * 참고.
     * annotation 은 상속을 할 수 없다.
     *
     * java.lang.annotation.Annotation interface 는 개발자가 직접 구현할 수 있는 것이 아니라,
     * Java 언어 자체에서 annotation 을 위한 기반으로 사용된다.
     * 제공 메서드
     *      boolean equals(Object obj);
     *          두개의 annotation 동일성을 비교
     *      int hashCode();
     *          annotation 의 해시코드 반환
     *          hashCode 값은 annotation 의 모든 요소 값에 기반한다.
     *      String toString();
     *          annotation 및 요소 정보 반환
     *      Class<? extends Annotation> annotationType();
     *          annotation type 반환
     *
     * 참고.
     * Annotation::equals, hashCode 는 기능의 차이는 없고 ..
     * 해시 자료구조에 사용될 수 있도록 구성해놓은 것인듯..?
     */
}
