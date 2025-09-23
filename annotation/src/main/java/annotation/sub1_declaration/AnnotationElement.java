package annotation.sub1_declaration;

import java.util.Arrays;

public class AnnotationElement {

    /**
     * reflection + annotation 기술을 사용하면 클래스에 추가정보(annotation element)에 접근하고 이를 런타임에 이용할 수 있다.
     */

    @Annotation1(value = "value1", count = 10, tags = {"tag1", "tag2"}, myEnum = MyEnum.ENUM2)
    private static class MyObject {

    }

    public static void main(String[] args) {

        Class<MyObject> myObjectClassMetadata = MyObject.class; // MyObject 타입의 메타데이터인 Class<MyObject> 타입(Class 클래스) 의 인스턴스를 얻었다.

        Annotation1 annotation = myObjectClassMetadata.getAnnotation(Annotation1.class); // MyObject 타입에 적용된 annotation 정보를 얻었다.

        System.out.println("annotation = " + annotation);
        System.out.println("annotation.value() = " + annotation.value());
        System.out.println("annotation.count() = " + annotation.count());
        System.out.println("annotation.myEnum() = " + annotation.myEnum());
        System.out.println("annotation.tags() = " + Arrays.toString(annotation.tags()));
    }
}
