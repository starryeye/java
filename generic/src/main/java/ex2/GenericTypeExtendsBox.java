package ex2;

import animal.Animal;

public class GenericTypeExtendsBox<T extends Animal> {

    /**
     * 타입 파라미터에 extends 키워드를 사용하여 타입 인자의 타입 상한을 정할 수 있다.
     */

    private T animal;

    public T getAnimal() {
        return animal;
    }

    public void setAnimal(T animal) {
        this.animal = animal;
    }

    public void animalSound() {
        if (animal == null) {
            System.out.println("Animal is null!");
        }

        animal.sound(); // 타입 상한을 정해두어서 animal 값의 타입이 최소 Animal 이므로 Animal 클래스의 메서드를 사용할 수 있다.
    }
}
