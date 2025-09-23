package reflection.sub6_constructor_metadata;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class ConstructorMetadata2 {

    /**
     * 클래스의 메타데이터(Class<T>)를 통해 해당 클래스의 생성자 정보를 조회하고
     * 생성자 정보로 객체 인스턴스를 생성할 수 있다.
     */

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<User> userClassMetadata = User.class; // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻었다.

        // Class<User> 타입의 인스턴스로 특정(파라미터가 한 개이고 파라미터 타입이 String) 생성자 정보를 얻었다.
        Constructor<User> constructor = userClassMetadata.getDeclaredConstructor(String.class);
        int modifiers = constructor.getModifiers();
        System.out.println("construct Modifier is private : " + Modifier.isPrivate(modifiers)); // 얻은 생성자가 private 접근 제어자인지 확인


        constructor.setAccessible(true); // field 와 마찬가지로 private 접근 제어자라도 접근할 수 있도록 설정할 수 있다.

        // 생성자 정보로 인스턴스를 생성
        User instance = constructor.newInstance("AAA");
        System.out.println("instance created.. = " + instance);
    }
}
