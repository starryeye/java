package dev.practice.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {

    /**
     * Java 는 "Class" 타입의 클래스가 존재한다.
     * <p>
     * 자바는 클래스와 인터페이스의 메타데이터를
     * java.lang 패키지에 소속된 "Class" 클래스로 관리한다.
     * (여기서의 메타데이터는 클래스 이름, 생성자 정보, 필드 정보, 메서드 정보를 뜻한다.)
     * <p>
     * Class 객체가 한 번 생성되면, JVM은 해당 클래스에 대해 단일 Class 객체만 유지한다.
     * 즉, Class 객체는 싱글톤이다.
     * 해당 인스턴스로 아래와 같은.. 리플랙션 기능을 사용할 수 있다.
     */

    public static void main(String[] args) throws ClassNotFoundException {

        /**
         * 클래스 로더에 의한 클래스 로딩이 끝나면, "Class" 타입의 인스턴스를 만들어서 힙에 넣어준다.
         */
        //타입 파라미터가 Book인 "Class" 타입의 인스턴스 ("Class" 타입의 인스턴스를 가져오는 방법1, 타입을 이용)
        Class<Book> bookClass = Book.class;

        Book book = new Book(); //application 에 인스턴스가 어딘가 존재..
        //타입 파라미터가 Book인 "Class" 타입의 인스턴스 ("Class" 타입의 인스턴스를 가져오는 방법2, 인스턴스를 이용)
        Class<? extends Book> aClass = book.getClass();

        // 타입 파라미터가 Book인 "Class" 타입의 인스턴스 ("Class" 타입의 인스턴스를 가져오는 방법3, FQCN 문자열을 이용)
        Class<?> aClass1 = Class.forName("dev.practice.reflection.Book");

        /**
         * Class 타입의 인스턴스로 다양한 정보를 빼올수 있다. (메타데이터)
         */
        Arrays.stream(bookClass.getFields()) //public 필드만..
                .forEach(System.out::println);
        Arrays.stream(bookClass.getDeclaredFields()) //모든 필드
                .forEach(System.out::println);
        Arrays.stream(bookClass.getMethods()) //public 메서드만.. (Object 의 메서드도 출력되는걸 볼 수 있다.)
                .forEach(System.out::println);

        //필드의 값도 빼올 수 있다. (Book 인스턴스가 필요하다.)
        Arrays.stream(bookClass.getDeclaredFields())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);//getDeclaredFields 로 접근하고 있기 때문에 접근이 불가능한 값들을 허용하도록 셋팅
                        System.out.println("key: " + field + " value: " + field.get(book));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

        // 부모 클래스의 "Class" 타입의 인스턴스도 받을 수 있다.
        System.out.println(MyBook.class.getSuperclass()); //Java 에서는 상속을 하나밖에 못 받으므로 리턴은 하나이다.

        // 인터페이스 정보도 받을 수 있다. "Class" 타입의 인스턴스 (인터페이스)
        Class<?>[] interfaces = MyBook.class.getInterfaces(); //Java 에서는 인터페이스 여러개를 하나의 클래스에서 구현할 수 있다.

        /**
         * getModifiers() 와 Modifier 의 스태틱 메서드를 이용하면
         * 해당 필드(메서드도 됨)에 적용된 키워드를 알아볼 수 있다..
         *
         */
        Arrays.stream(Book.class.getFields()) //MyBook 으로 하면 아무것도 안찍힘.. (TODO 생각해보기)
                .forEach(field -> {
                    int modifiers = field.getModifiers(); //
                    System.out.println(modifiers);
                    System.out.println(Modifier.isFinal(modifiers));
                    System.out.println(Modifier.isPrivate(modifiers));
                    System.out.println(Modifier.isStatic(modifiers));
                });

        // 필드에 선언되어있는 어노테이션도 빼올 수 있다.
        Arrays.stream(bookClass.getFields())
                .forEach(field -> {
                    Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                });
    }
}
