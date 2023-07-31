package dev.practice.java8.two;

public class Foo {

    public static void main(String[] args) {

        /**
         * 자바에서의 함수형 프로그래밍은 아래를 지켜야한다.
         * - 함수를 일급 객체(First Class Object) 로 사용할 수 있다.
         * - 순수 함수 (pure function)
         * - - 사이드 이펙트를 만들 수 없다. (함수 밖에 있는 값을 변경하면 안된다.)
         * - - 상태가 없다. (상태는 함수 밖에 존재해야한다.)
         * - 고차 함수 (High-Order Class)
         * - - 함수가 함수를 매개변수로 받을 수 있고 함수가 함수를 리턴할 수 있다.
         * - 불변성
         *
         * 함수형 프로그래밍(functional programming)
         * - 함수형 프로그래밍은 자료 처리를 수학적 함수의 계산으로 취급하고 상태와 가변 데이터를 멀리하는 프로그래밍 패러다임의 하나이다.
         * 명령형 프로그래밍에서는 상태를 바꾸는 것을 강조하는 것과는 달리, 함수형 프로그래밍은 함수의 응용을 강조한다.
         * 프로그래밍이 문이 아닌 식이나 선언으로 수행되는 선언형 프로그래밍 패러다임을 따르고 있다.
         */

        RunSomething runSomething = (number) -> {
            return number + 10;
        };
        // 입력 받은 값이 같으면 결과는 동일해야한다.
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
    }
}
