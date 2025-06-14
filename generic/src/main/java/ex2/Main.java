package ex2;

import animal.Dog;

public class Main {

    public static void main(String[] args) {

        GenericTypeExtendsBox<Dog> dogBox = new GenericTypeExtendsBox<>();

        dogBox.setAnimal(new Dog("강아지", 1));
        /**
         * dogBox.setAnimal(new Cat("고양이", 1)); // compile error
         * 제네릭 타입을 사용하지 않고 다형성만으로 Box 를 만들었다면, dogBox.setAnimal() 에 Cat 을 전달하면 컴파일 에러가 발생하지 않는다.
         */

        Dog dog = dogBox.getAnimal();
        /**
         * 다형성만으로 Box 를 만들었다면, dogBox.getAnimal() 에서 Animal 타입이 리턴되어 다운 캐스팅이 필요했을 것이다.
         */

//        GenericTypeExtendsBox<Integer> integerBox = new GenericTypeExtendsBox<Integer>(); // compile error
        /**
         * 타입 파라미터에 extends 키워드로 타입 인자의 상한 타입을 정해 놓아서 Animal 타입이나 Animal 타입을 상속받은 타입이 아니면 컴파일 에러가 발생한다.
         */

        dogBox.animalSound();
        /**
         * 메서드 내부 참고
         */
    }
}
