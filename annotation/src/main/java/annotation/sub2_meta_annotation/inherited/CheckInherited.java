package annotation.sub2_meta_annotation.inherited;

import java.lang.annotation.Annotation;

public class CheckInherited {

    /**
     * A class 는 @InheritedAnnotation1, @NoInheritedAnnotation1 를 적용하였다.
     * I interface 는 @InheritedAnnotation2, @NoInheritedAnnotation2 를 적용하였다.
     *
     * B class 는 A 를 상속하고, I 를 구현하였다.
     *
     * getAnnotations
     *      해당 클래스에 직접 적용된 annotation 을 포함하고, 해당 클래스가 상속한 클래스에 적용된 annotation 까지 포함
     *      해당 클래스가 구현한 인터페이스에 적용된 annotation 은 포함되지 않는다.
     * getDeclaredAnnotations
     *      해당 클래서에 직접 적용된 annotation 만 반환된다.
     *
     * todo
     *      reflection 에서 field, method 는 상속 뿐만아니라 구현 인터페이스의 정보까지 받아올 수 있음에도.. annotation 은 되지 않는이유?
     *      Spring 에서 @Transactional 은 interface 에 적용해도 구현체에서 @Transactional 이 적용되는 이유?
     */

    public static void main(String[] args) {

        Class<B> bClassMetadata = B.class;

        System.out.println("======= getAnnotations ========");
        for (Annotation annotation : bClassMetadata.getAnnotations()) {
            System.out.println(annotation); // @InheritedAnnotation1 만 출력됨
        }

        System.out.println("======= getDeclaredAnnotations ========");
        for (Annotation annotation : bClassMetadata.getDeclaredAnnotations()) {
            System.out.println(annotation); // 출력되는 것 없음
        }
    }
}
