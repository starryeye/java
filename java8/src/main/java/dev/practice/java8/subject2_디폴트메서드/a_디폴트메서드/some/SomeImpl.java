package dev.practice.java8.subject2_디폴트메서드.a_디폴트메서드.some;

public class SomeImpl implements Some, Some2{

    /**
     * Some, Some2 에는 동일한 default 메서드 printNameUpperCase 가 구현되어있다.
     * 두 인터페이스를 모두 상속 받은 SomeImpl 에서.. Java 는 무엇을 써야할지 모르기 때문에 컴파일에러를 만든다.
     * 이를 해결 하기 위해서는 SomeImpl 에서 해당 default 메서드를 구현하면 된다.
     */

    @Override
    public void printNameUpperCase() {
        Some.super.printNameUpperCase();
    }
}
