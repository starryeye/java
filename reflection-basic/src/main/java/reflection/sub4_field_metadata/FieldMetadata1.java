package reflection.sub4_field_metadata;

import java.lang.reflect.Field;

public class FieldMetadata1 {

    /**
     * 클래스의 메타데이터(Class<T>)를 통해 해당 클래스의 필드 정보를 조회할 수 있다.
     *
     * Class<T>::getFields()
     *      T 타입 클래스에 선언된 public 필드
     *      T 타입 클래스가 상속한 모든 클래스에 선언된 public 필드
     * Class<T>::getDeclaredFields()
     *      T 타입 클래스에 선언된 모든 필드 (public, protected, default(package-private), private)
     */

    public static void main(String[] args) {

        Class<User> userClassMetadata = User.class; // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻었다.

        System.out.println("=========== userClassMetadata.getFields() ===========");
        Field[] fields = userClassMetadata.getFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        System.out.println("=========== userClassMetadata.getDeclaredFields() ===========");
        Field[] declaredFields = userClassMetadata.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("declaredField = " + field);
        }
    }
}
