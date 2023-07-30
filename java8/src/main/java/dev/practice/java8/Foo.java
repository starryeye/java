package dev.practice.java8;

public class Foo {

    public static void main(String[] args) {

        /**
         * 아래는 익명 내부 클래스이다. Anonymous inner class
         */
        RunSomething runSomething1 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("doIt");
            }
        };
        runSomething1.doIt();

        /**
         * 람다 표현식을 사용하면 함수형 인터페이스의 인스턴스를 만들 수 있다.
         *
         * () -> System.out.println("doIt"); 는 특수한 형태의 "오브젝트" 이다.
         * 따라서 해당 객체를 파라미터, 리턴 타입으로 사용가능하다.
         * 함수형 인터페이스를 in-line 으로 구현한 오브젝트이다.
         */
        RunSomething runSomething2 = () -> System.out.println("doIt");
        runSomething2.doIt();
    }
}
