package reflection.sub4_field_metadata;

import java.lang.reflect.Field;

public class FieldMetadata2 {

    /**
     * 클래스의 메타데이터(Class<T>)를 통해 해당 클래스의 필드 정보를 조회하여..
     * 필드 값을 변경할 수 있다.
     *
     */

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        User user = new User("AAA");
        System.out.println("user name = " + user.getName());


        Class<? extends User> userClassMetadata = user.getClass(); // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻었다.
        Field nameField = userClassMetadata.getDeclaredField("name");// 런타임에 동적으로 필드의 정보를 얻음

        nameField.setAccessible(true); // private 필드에 접근을 허용한다.
        nameField.set(user, "BBB"); // field 메타데이터를 통해 필드 값을 변경할 수 있다. (해당 필드를 변경할 인스턴스와 변경할 값)
        System.out.println("user name = " + user.getName());
    }
}
