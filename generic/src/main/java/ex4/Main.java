package ex4;

import animal.Animal;
import animal.Dog;

public class Main {

    public static void main(String[] args) {

        Box<Dog> dogBox = new Box<>();
        dogBox.set(new Dog("멍", 1));

        WildcardAndGenericMethodUtil.printWithGeneric(dogBox);
        WildcardAndGenericMethodUtil.printWithWildcard(dogBox);
        WildcardAndGenericMethodUtil.printWithGeneric2(dogBox);
        WildcardAndGenericMethodUtil.printWithWildcard2(dogBox);
        /**
         * 위 두가지 케이스에 대해서는 제네릭 타입을 사용하기 위해
         * 제네릭 메서드를 사용하던 와일드 카드를 사용하던 동일한 결과를 얻을 수 있다.
         */


        Dog dog = WildcardAndGenericMethodUtil.<Dog>printAndReturnGeneric(dogBox);
        Animal animal = WildcardAndGenericMethodUtil.printAndReturnWildcard(dogBox);
        /**
         * 여기서 달라진다.
         * 제네릭 메서드를 사용한 경우는 제네릭 메서드 호출 시점에 Dog 타입을 사용했기 때문에 리턴 타입도 Dog 로 정해지지만,
         * 와일드 카드를 사용한 경우(<? extends Animal>) 단순히 상한 타입으로 리턴된다. (제네릭 처럼 타입이 정해지는 것이 아님, 제네릭 선언과 아무런 연관이 없음)
         */
    }
}
