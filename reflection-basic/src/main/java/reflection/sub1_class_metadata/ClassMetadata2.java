package reflection.sub1_class_metadata;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ClassMetadata2 {

    /**
     * 클래스의 메타데이터를 접근하여 어떤 정보를 얻을 수 있는지 알아본다.
     *      클래스 이름, 패키지, 부모 클래스, 구현한 인터페이스 목록, 수정자 정보 등을 얻을 수 있다.
     *      또한 메서드, 필드, 애너테이션 정보도 얻을 수 있다.
     *          -> sub2, sub3, sub4 ... 로 각각 더 자세하게 다룬다.
     *
     *
     * 참고.
     * Java 수정자
     *      Java 의 수정자는 접근 제어자와 비 접근 제어자로 나눌 수 있다.
     *      접근 제어자
     *          public, protected, default(package-private), private
     *      비 접근 제어자
     *          static, final, abstract, synchronized, volatile
     */

    public static void main(String[] args) {

        // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻었다.
        Class<User> userClassMetadata = User.class;


        // 메타데이터 조회
        System.out.println("userClassMetadata.getName() = " + userClassMetadata.getName());
        System.out.println("userClassMetadata.getSimpleName() = " + userClassMetadata.getSimpleName());
        System.out.println("userClassMetadata.getPackage() = " + userClassMetadata.getPackage());

        System.out.println("userClassMetadata.getSuperclass() = " + userClassMetadata.getSuperclass());
        System.out.println("userClassMetadata.getInterfaces() = " + Arrays.toString(userClassMetadata.getInterfaces()));

        System.out.println("userClassMetadata.isInterface() = " + userClassMetadata.isInterface());
        System.out.println("userClassMetadata.isEnum() = " + userClassMetadata.isEnum());
        System.out.println("userClassMetadata.isAnnotation() = " + userClassMetadata.isAnnotation());

        // 수정자 정보를 얻을 수 있다.
        int modifiers = userClassMetadata.getModifiers();
        System.out.println("userClassMetadata.getModifiers() = " + modifiers);
        System.out.println("Modifier.isPublic(modifier) = " + Modifier.isPublic(modifiers));
        System.out.println("Modifier.toString(modifier) = " + Modifier.toString(modifiers));


        System.out.println("userClassMetadata.getMethods() = " + Arrays.toString(userClassMetadata.getMethods()));
        System.out.println("userClassMetadata.getDeclaredMethods() = " + Arrays.toString(userClassMetadata.getDeclaredMethods()));
        System.out.println("userClassMetadata.getFields() = " + Arrays.toString(userClassMetadata.getFields()));
        System.out.println("userClassMetadata.getDeclaredFields() = " + Arrays.toString(userClassMetadata.getDeclaredFields()));
        System.out.println("userClassMetadata.getAnnotations() = " + Arrays.toString(userClassMetadata.getAnnotations()));
    }
}
