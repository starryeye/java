package dev.practice.sub4_di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

//Test 코드 참고
public class ContainerService {

    /**
     * 타입 파라미터가 T 인 "Class" 타입 클래스 객체(classType)가 들어오면, T 타입의 클래스 인스턴스를 리턴한다.
     *
     * JVM 에서 "Class" 타입 클래스 객체는 싱글톤으로 유일하지만,
     * 현재, 아래와 같이 리턴하는 인스턴스는 싱글톤이 아니다.
     */
    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType); // 인스턴스 생성

        // DI 를 하기 위해, classType 의 필드를 순회 하면서, @Inject 어노테이션이 있는지 검사한다.
        Arrays.stream(classType.getDeclaredFields())
                .forEach(field -> {

                    Inject annotation = field.getAnnotation(Inject.class);
                    if(annotation != null) {
                        Object fieldInstance = createInstance(field.getType()); // 필드의 class 타입을 얻어서 인스턴스 생성
                        field.setAccessible(true); // 필드의 접근제어자는 public 이 아닐수 도 있다.
                        try {
                            field.set(instance, fieldInstance); // 필드에 인스턴스 주입
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor().newInstance(); // default constructor
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
