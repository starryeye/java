package ex3;

import animal.Animal;

public abstract class GenericMethodUtil {

    private GenericMethodUtil() {}

    public static Animal animalMethod(Animal animal) {
        System.out.println("animal: " + animal);
        animal.sound();
        return animal;
    }

    public static <T extends Animal> T genericMethod(T t) {
        System.out.println("genericMethod: " + t);
        t.sound(); // generic 상한을 사용하여 Animal 메서드를 사용가능 하다.
        return t;
    }

    /**
     * 제네릭 타입은 클래스나 인터페이스 같이 "타입" 옆에 <T> 가 존재하는데..
     * 제네릭 메서드는 메서드 리턴 타입 왼쪽에 <T> 가 존재한다.
     *
     * 메서드가 호출 되는 시점에 타입 인자를 전달하여 타입 파라미터가 정해진다.
     * -> 주의, 메서드가 호출되는 시점에 타입 파라미터가 정해지므로.. 만약 위와 같이 static 메서드를 제네릭 메서드로 만든 경우..
     *      static 메서드에서는 인스턴스가 만들어지지 않았으므로 제네릭 타입의 타입 파라미터는 사용이 불가능하다.
     */
}
