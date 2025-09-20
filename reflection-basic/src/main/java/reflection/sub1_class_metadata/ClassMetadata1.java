package reflection.sub1_class_metadata;

public class ClassMetadata1 {

    /**
     * 클래스의 메타데이터를 조회하는 방법을 알아본다.
     *
     * 클래스의 메타데이터..
     *      클래스 이름, 접근 제어자, 부모 클래스, 구현한 인스턴스 목록 등을 담고 있다.
     *      Class<T> 라는 클래스 타입으로 접근할 수 있다.
     *
     * Class<T> 라는 클래스 타입은 런타임에 JVM 에 의해 힙 메모리에 생성된다.
     *      Class<T> 는 JVM 에 로드된 타입(클래스/인터페이스/배열/원시타입/void)의 런타임 미러 객체다.
     *      Class<T> 가 접근할 실제 데이터는 Metaspace 라는 공간에 존재함
     *
     * todo..
     *      Class<T> vs Type vs ParameterizedType
     */

    public static void main(String[] args) throws ClassNotFoundException {

        // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻는 방법 1
        Class<User> userClassMetadata1 = User.class;
        System.out.println("userClass1: " + userClassMetadata1);


        // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻는 방법 2
        User userInstance = new User();
        Class<? extends User> userClassMetadata2 = userInstance.getClass();
        System.out.println("userClass2: " + userClassMetadata2);


        // User 클래스의 메타데이터를 접근할 수 있는, Class<User> 타입의 클래스를 얻는 방법 3
        String classPath = "reflection.sub1_class_metadata.User";
        Class<?> userClassMetadata3 = Class.forName(classPath);
        System.out.println("userClass3: " + userClassMetadata3);
    }
}
