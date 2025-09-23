package reflection.sub6_constructor_metadata;

import java.lang.reflect.Constructor;

public class ConstructorMetadata1 {

    /**
     * 클래스의 메타데이터(Class<T>)를 통해 해당 클래스의 생성자 정보를 조회할 수 있다.
     *
     * Class<T>::getConstructors()
     *      T 타입 클래스에 선언된 public 생성자
     * Class<T>::getDeclaredConstructors()
     *      T 타입 클래스에 선언된 모든 생성자 (public, protected, default(package-private), private)
     *
     * 참고
     * method, field 정보를 얻을 때는 상속하거나 구현한 클래스의 정보들도 함께 얻을 수 있었지만..
     * 생성자는 상속 개념이 없다.
     * 따라서 getConstructors() 로 조회 시, 자기 자신의 public 생성자만 보여준다.
     */

    public static void main(String[] args) {

        Class<User> userClassMetadata = User.class; // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻었다.

        System.out.println("=========== userClassMetadata.getConstructors() ===========");
        Constructor<?>[] constructors = userClassMetadata.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("=========== userClassMetadata.getDeclaredConstructors() ===========");
        Constructor<?>[] declaredConstructors = userClassMetadata.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

    }
}
