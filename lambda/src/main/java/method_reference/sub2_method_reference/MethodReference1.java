package method_reference.sub2_method_reference;

import java.util.function.*;

public class MethodReference1 {

    /**
     * 메서드 레퍼런스
     *      람다 식 없이 이미 정의된 메서드를 람다 식으로 변환해주는 문법적 편의 기능이다.
     *      람다 식이 단순 메서드 호출만 할 경우 람다 식을 메서드 레퍼런스 방식으로 변경 할 수 있다.
     *
     * 메서드 레퍼런스의 유형 4가지를 String 객체를 통해 알아본다.
     *
     * 1. 정적 메서드 참조 (Static Method Reference)
     *      정적 메서드를 참조한다.
     *      문법
     *          클래스명(타입 명)::static메서드명
     *
     * 2. 특정 객체의 인스턴스 메서드 참조 (Instance Method of Particular Object)
     *      특정 객체의 인스턴스의 인스턴스 메서드를 참조한다.
     *      문법
     *          인스턴스변수명::인스턴스메서드명
     *
     * 3. 생성자 참조 (Constructor Reference)
     *      생성자를 참조한다.
     *      문법
     *          클래스명(타입 명)::new
     *
     * 4. 특정 타입의 임의 객체의 인스턴스 메서드 참조 (Instance Method of Arbitrary Object)
     *      매개변수로 넘어온 인스턴스의 인스턴스 메서드를 참조한다.
     *      문법
     *          클래스명(타입 명)::인스턴스메서드명
     *
     *
     * 4번("특정 타입의 임의 객체의 인스턴스 메서드 참조")에 대한 이해..
     *      2번을 보면, 메서드 참조 시점에 이미 어떤 인스턴스로 호출할지 정해진다.
     *          메서드 레퍼런스 문법에 "인스턴스 변수명"이 고정됨
     *      1번도, 메서드 참조 시점에 이미 어느 클래스의 정적 메서드를 호출할지 정해지는 것으로 볼 수 있다.
     *          메서드 레퍼런스 문법에 "클래스명(타입 명)"이 고정됨.
     *      하지만, 4번의 경우에는 메서드 참조 시점에 어떤 인스턴스 메서드가 호출될지는 정해지지만...
     *      어떤 인스턴스를 이용하는지는 정해지지 않고 람다를 실행하는 시점에 정해지는 것을 볼 수 있다.
     *          람다 실행시점에 파라미터로 인스턴스가 전달됨.
     *      실행 시점이 되어야 어떤 객체을 이용하는지 정해져서 네이밍이.. "특정 타입의 임의 객체의 인스턴스 메서드 참조" 이다...
     *
     *
     * 참고.
     * 개발하다 보면, 4번을 가장 많이 이용함
     */

    public static void main(String[] args) {



        // 1. 정적 메서드 참조 (Static Method Reference)
        // ClassName::staticMethod
        IntFunction<String> intToString = String::valueOf; // intValue -> String.valueOf(intValue) 와 동일
        System.out.println("result 1 = " + intToString.apply(123));



        // 2. 특정 객체의 인스턴스 메서드 참조 (Instance Method of Particular Object)
        // instance::instanceMethod
        String stringInstance = "Hello";
        IntSupplier getLength = stringInstance::length; // () -> stringInstance.length() 와 동일
        System.out.println("result 2 = " + getLength.getAsInt());




        // 3. 생성자 참조 (Constructor Reference)
        // ClassName::new
        Supplier<String> charToString = String::new; // () -> new String() 와 동일
        System.out.println("result 3 = " + charToString.get());




        // 4. 특정 타입의 임의 객체의 인스턴스 메서드 참조 (Instance Method of Arbitrary Object)
        // ClassName::instanceMethod
        UnaryOperator<String> toUpper = String::toUpperCase; // s -> s.toUpperCase() 와 동일
        System.out.println("result 4 = " + toUpper.apply("hello"));
        // 매개 변수로 넘어온 객체 인스턴스("hello")의 인스턴스 메서드(toUpperCase) 를 호출
    }
}
