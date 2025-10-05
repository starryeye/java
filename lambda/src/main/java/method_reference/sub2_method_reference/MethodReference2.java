package method_reference.sub2_method_reference;

import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference2 {

    /**
     * 메서드 레퍼런스의 유형 4가지를 직접 만든 Person 객체를 통해 알아본다.
     */

    public static void main(String[] args) {


        // 1. 정적 메서드 참조
        Supplier<String> staticMethod = Person::greeting; // () -> Person.greeting() 와 동일
        System.out.println("result 1 = " + staticMethod.get());



        // 2. 특정 객체의 인스턴스 메서드 참조
        Person person1 = new Person("Kim");
        Supplier<String> instanceMethod1 = person1::introduce; // () -> person1.introduce() 와 동일
        System.out.println("result 2 = " + instanceMethod1.get());



        // 3. 생성자 참조
        Supplier<Person> newPerson = Person::new; // () -> new Person() 와 동일
        System.out.println("result 3 = " + newPerson.get());



        // 4. 특정 타입의 임의 객체의 인스턴스 메서드 참조
        Function<Person, String> instanceMethod2 = Person::introduce; // person -> person.introduce() 와 동일
        Person person2 = new Person("Lee");
        System.out.println("result 4 = " + instanceMethod2.apply(person2)); // 실행 시점이 되어서야 어떤 인스턴스(person2)를 사용할지 정해짐
    }
}
