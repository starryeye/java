package reflection.sub2_method_metadata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodMetadata2 {

    /**
     * 클래스의 메타데이터(Class<T>)를 통해 해당 클래스의 메서드 정보를 조회하여..
     * 메서드를 호출 할 수 도 있다. -> "동적 메서드 호출"이라 부른다.
     *
     * 참고.
     * 여기서의 "동적"이다 라는 의미는.. sub3_dynamic 패키지를 보자..
     */

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // reflection 을 이용하지 않고 일반적으로 호출하는 방법.. "정적 메서드 호출"이라 부른다.
        User userInstance1 = new User();
        userInstance1.changeName("AAA"); // 정적 메서드 호출
        System.out.println("userInstance1.getGreet = " + userInstance1.getGreet("Hello")); // 호출이 정상적이었는지 확인





        // reflection 을 이용하여 "동적" 메서드를 호출하는 방법
        String methodName = "changeName"; // 호출 하고 싶은 메서드 이름
        User userInstance2 = new User(); // 호출할 User 인스턴스
        Class<? extends User> userClassMetadata = userInstance2.getClass(); // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻었다.
        Method method = userClassMetadata.getDeclaredMethod(methodName, String.class);// 호출 하고 싶은 메서드이름과 해당 메서드의 파라미터 타입으로 메서드를 조회한다.
        method.invoke(userInstance2, "BBB"); // reflection 기술로 얻은 메서드를 호출한다. "동적" 메서드 호출
        System.out.println("userInstance2.getGreet = " + userInstance2.getGreet("Hello"));


    }
}
