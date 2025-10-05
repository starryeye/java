package method_reference.sub2_method_reference;

import java.util.function.*;

public class MethodReference3 {

    /**
     * 메서드 레퍼런스의 유형 4가지에서 매개변수가 필요한 경우를 직접 만든 Person 객체를 통해 알아본다.
     *
     * 1, 2, 3 번은..
     *      메서드 참조에서는 파라미터가 없는 것처럼 하고 실행 시점에 파라미터를 순서대로 넘겨주면된다.
     * 4 번은..
     *      메서드 참조에서는 파라미터가 없는 것처럼 하고 실행 시점에 첫번째 파라미터로 사용할 인스턴스를 넘기고
     *      이후, 인스턴스 메서드의 파라미터를 순서대로 넘겨주면된다.
     */

    public static void main(String[] args) {


        // 1. 매개변수가 존재하는 정적 메서드 참조
        UnaryOperator<String> staticMethod = Person::greetingWithName; // name -> Person.greetingWithName(name) 와 동일
        System.out.println("result 1 = " + staticMethod.apply("kim"));



        // 2. 특정 객체의 매개변수가 존재하는 인스턴스 메서드 참조
        Person person1 = new Person("Kim");
        IntFunction<String> instanceMethod = person1::introduceWithNumber; // n -> person.introduceWithNumber(n) 와 동일
        System.out.println("result 2 = " + instanceMethod.apply(1));



        // 3. 매개변수가 존재하는 생성자 참조
        Function<String, Person> newPerson = Person::new; // name -> new Person(name) 와 동일
        System.out.println("result 3 = " + newPerson.apply("kim"));



        // 4. 특정 타입의 임의 객체의, 매개변수가 존재하는 인스턴스 메서드 참조
        BiFunction<Person, Integer, String> instanceMethod2 = Person::introduceWithNumber; // (Person p, Integer number) -> p.introduceWithNumber(number) 와 동일
        Person person2 = new Person("Lee");
        System.out.println("result 4 = " + instanceMethod2.apply(person2, 1)); // 실행 시점이 되어서야 어떤 인스턴스(person2)를 사용할지 정해짐
        // 메서드 참조와 동일한 람다식을 보면 이해가 될 것이다..
        //      첫번째 매개변수에는 어떤 인스턴스를 사용할 것인지에 대한 것이고 두번째 부터는 해당 인스턴스 메서드의 매개변수가 순서대로 표기된다.
    }
}
