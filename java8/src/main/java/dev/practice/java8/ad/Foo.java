package dev.practice.java8.ad;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo {

    public static void main(String[] args) {
        /**
         * 람다에 대해 자세히 알아보자.
         *
         * 람다 표현식은 "(인자) -> {바디}" 로 구성된다.
         * - 인자는 컴마를 구분자로 리스트 형태로 줄 수 있다.
         * - 바디는 한줄이면 중괄호를 생략할 수 있다.
         *
         * 인자 리스트
         * - 인자가없을때:()
         * - 인자가 한개일 때: (one) 또는 one
         * - 인자가 여러개 일 때: (one, two)
         * - 인자의 타입은 생략 가능, 컴파일러가 함수형 인터페이스의 제네릭을 보고 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)
         *
         * 바디
         * - 화살표 오른쪽에 함수 본문을 정의한다.
         * - 여러 줄인 경우에 {} 를사용해서묶는다.
         * - 한 줄인 경우에 생략 가능, return 도 생략 가능.
         */
    }

    private void run() {
        /**
         * 변수 캡쳐
         *
         * 로컬 변수 캡처
         * - final 이거나 effective final 인 경우에만 참조할 수 있다.
         * - 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일가 방지한다.
         *
         * effective final
         * - 이것도 역시 자바 8부터 지원하는 기능으로 “사실상" final 인 변수.
         * - final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
         *
         * 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다.
         * - 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.
         *
         * 위 설명은 책의 설명이다..
         * 아래 설명은 내가 쉽게 풀어봤다.
         */

        /**
         * History
         * 로컬 클래스, 익명 클래스 내부에서 외부 로컬 변수를 참조 하기 위해서는..
         * Java 8 이전에서는 아래 baseNumber 변수처럼 로컬 변수에 final 키워드가 반드시 필요했다.
         *
         * Current
         * Java 8 이후에는 참조하는 로컬 변수가 "effective final" 일 경우, final 을 생략할 수 있게 되었다. (로컬 클래스, 익명 클래스, 람다)
         * effective final : final 키워드는 없지만, 초기화 된 이후 변수의 값을 변경하지 않는 경우이다.
         *
         * 로컬 클래스, 익명 클래스 vs 람다
         * - 람다는 스코프(중괄호) 가 의미가 없다.. 쉐도잉 현상이 없음, 스코프 외부의 변수와 동일한 변수명을 사용하면 가려지는 현상이 없음
         */
        int baseNumber = 10;
        int shadow = 20;

        // 로컬 클래스에서 로컬 변수 참조
        class LocalClass {
            void printBaseNumber() {
                int shadow = 30;
                System.out.println(baseNumber + shadow); // 10 + 30
            }
        }

        // 익명 클래스에서 로컬 변수 참조
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int shadow = 30;
                System.out.println(integer + baseNumber + shadow); // integer + 10 + 30
            }
        };

        // 람다에서 로컬 변수 참조
        IntConsumer printInt = (i) -> {
//            int shadow = 30; // 컴파일 에러!!
            System.out.println(i + baseNumber + shadow); // i + 10 + 20
        };

//        baseNumber++; // 컴파일 에러!! effective final 조건 불만족
    }
}
