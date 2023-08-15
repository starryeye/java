package dev.practice.java8.subject2.a;

public interface Foo {

    void printName();

    String getName();

    /**
     * Foo 의 구현체가 printName, getName 을 구현하고 있는 상태에서
     * 아래 printNameUpperCase 추상메서드를 추가하면.. FooImpl 클래스에 컴파일에러가 생긴다. (추상메서드는 구현을 강제하기 때문)
     * 그래서 default 메서드로 하위 호환성을 제공하면서 컴파일 에러가 생기지 않게 한다.
     * -> 추후 하위 클래스가 오버라이딩을 마치면 추상 메서드로 변경해준다.
     */
//    void printNameUpperCase();

    /**
     * 사실.. default 메서드를 사용하면 좀 위험할 수 있다.
     * getName() 으로 반환된 값이 구현체에 따라 기능적으로나 안정성이 보장되지 않음 (null 을 리턴하는 구현체가 있을수있다..)
     * 따라서, 아래와 같이 @implSpec 으로 구현해달라는 요청과 기능을 상세하게 명시해준다..
     *
     * @implSpec
     * 이 구현체는 getName() 으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    // --

    /**
     * Object 에 존재하는 메서드(toString, equals 등) 들은 인터페이스에서 default 메서드로 재정의 할 수 없다.
     * -> 컴파일 에러
     * 추상 메서드로 선언하는 것은 상관 없다.
     */
//    default String toString() {
//
//    }

    // --

    /**
     * 인터페이스에서 static 메서드를 사용할 수 있다.
     */
    static void printAnyThing(String thing) {
        System.out.println(thing);
    }


}
