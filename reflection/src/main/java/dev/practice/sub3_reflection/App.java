package dev.practice.sub3_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class App {

    /**
     * 리플랙션("Class" 타입의 클래스 인스턴스 API)을 이용하여 클래스 정보를 수정하거나 실행해본다.
     */

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // Book.class 처럼 직접 참조할 수 없는 경우에 아래 방법을 쓰면되는 것이다.
        Class<?> aClass = Class.forName("dev.practice.sub3_reflection.Book");

        Arrays.stream(aClass.getDeclaredConstructors())
                .forEach(System.out::println); //Book 클래스의 생성자 정보 조회 (접근제어자, 파라미터 타입 등)

        // 리플랙션을 이용하면 생성자에서 파라미터 타입과 이름을 조회할 수 있다.
        Arrays.stream(aClass.getDeclaredConstructors())
                .forEach(constructor -> {
                    Arrays.stream(constructor.getParameters())
                            .forEach(parameter -> {
                                System.out.println("parameter name : " + parameter.getName());
                                System.out.println("parameter type : " + parameter.getType());
                            });
                });

        /**
         * 리플랙션을 이용하여 Book 인스턴스를 생성
         *
         * 리플랙션에서 다른 것들(필드, 메서드) 과는 다르게..
         * 생성자는 이름이 없으므로 이름으로 조회하는 것이 아닌 파라미터 조합으로 조회를 할 수 있다.
         */
        // Class.newInstance() 는 deprecated 되었으므로 아래 방법을 사용한다.
        Constructor<?> constructor = aClass.getConstructor(); // default constructor 이므로 파라미터로 넘겨주는게 없다.
        Book book = (Book) constructor.newInstance();

        // 파라미터를 받는 생성자를 이용하는 방법
        Constructor<?> constructor1 = aClass.getDeclaredConstructor(String.class);// String 타입 하나를 파라미터로 하는 생성자, private 이므로 getDeclaredConstructructor 를 사용
        constructor1.setAccessible(true); //private 이므로..
        Arrays.stream(constructor1.getParameterTypes()) //파라미터 타입 정보를 조회 할 수 있다.
                .forEach(System.out::println);
        Book book1 = (Book) constructor1.newInstance("myBook"); //생성자 파라미터 값을 넘겨줘야한다.

        /**
         * 리플랙션을 이용하여 필드값을 조회하고 변경
         */
        Class<Book> bookClass = Book.class;
        Field a = bookClass.getDeclaredField("A"); //변수 명이 A인 필드 조회
        //static 변수면, 인스턴스마다 다른 변수가 아닌.. 모든 인스턴스가 공유하는 변수이다.
        //정확하게는 인스턴스가 생성되지 않아도 존재한다. get 메서드 파라미터로 null 을 넘겨준다. (원래는 조회할 Book 인스턴스를 넘겨줌)
        System.out.println(a.get(null));
        a.set(null, "AAAAA"); //마찬가지로 static 변수이므로 null 이다. (원래는 조회할 Book 인스턴스를 넘겨줌)
        System.out.println(a.get(null));

        Field b = bookClass.getDeclaredField("B"); // getField 는 public 만 접근 하므로..
        b.setAccessible(true); // 변수 B 는 접근제어자가 private 이므로... setAccessible 메서드를 통해 접근이 가능하도록 변경해준다.
        System.out.println(b.get(book1)); //해당 필드는 Book 인스턴스가 있어야 존재할 수 있는 필드이다. 파라미터로 Book 인스턴스를 넘겨준다.
        b.set(book1, "BBBBB"); // 마찬가지로 Book 인스턴스가 필요하다.
        System.out.println(b.get(book1));

        /**
         * 리플랙션을 이용하여 메서드를 실행할 수 있다.
         */
        Method c = Book.class.getDeclaredMethod("c");// getMethod 는 public 만 접근 가능하므로..
        c.setAccessible(true); //private 메서드 이므로..
        c.invoke(book1); // static 메서드가 아니므로 인스턴스가 필요하기 때문에 Book 인스턴스를 넘겨줬다. c 메서드는 파라미터가 없어서 더이상의 파라미터는 넘겨주지 않았다.

        Method d = Book.class.getDeclaredMethod("sum", int.class, int.class); // getMethod 를 써도 된다. 파라미터가 존재하는 메서드는 파라미터의 타입도 넘겨줘야한다.
        //static 메서드가 아니므로 인스턴스가 필요하기 때문에 Book 인스턴스를 넘겨줬다. 파라미터가 존재하는 메서드이므로 파라미터를 넘겨주었다.
        int invoke = (int) d.invoke(book1, 100, 200);
        System.out.println(invoke);
    }
}
