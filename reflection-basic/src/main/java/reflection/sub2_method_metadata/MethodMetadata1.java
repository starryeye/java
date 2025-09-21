package reflection.sub2_method_metadata;

import java.lang.reflect.Method;

public class MethodMetadata1 {

    /**
     * 클래스의 메타데이터(Class<T>)를 통해 해당 클래스의 메서드 정보를 조회할 수 있다.
     *
     * Class<T>::getMethods()
     *      T 타입 클래스에 선언된 public 메서드
     *      T 타입 클래스가 상속한 모든 클래스에 선언된 public 메서드
     * Class<T>::getDeclaredMethods()
     *      T 타입 클래스에 선언된 모든 메서드 (public, protected, default(package-private), private)
     */

    public static void main(String[] args) {

        Class<User> userClassMetadata = User.class; // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻었다.

        System.out.println("=========== userClassMetadata.getMethods() ===========");
        Method[] methods = userClassMetadata.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("=========== userClassMetadata.get() ===========");
        Method[] declaredMethods = userClassMetadata.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("declaredMethods = " + method);
        }
    }
}
