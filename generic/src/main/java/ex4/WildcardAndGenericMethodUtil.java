package ex4;

import animal.Animal;

public abstract class WildcardAndGenericMethodUtil {

    /**
     * 와일드카드는 제네릭 타입이나 제네릭 메서드를 선언하는 것이 아니다.
     * 이미 만들어진 제네릭 타입을 편하게 사용하기 위해 존재한다.
     */

    private WildcardAndGenericMethodUtil() {}

    public static <T> void printWithGeneric(Box<T> box) { // 제네릭 메서드 선언
        System.out.println("T = " + box.get());
    }

    public static void printWithWildcard(Box<?> box) { // 제네릭 타입을 편하게 사용하기 위해 와일드 카드 사용
        System.out.println("? = " + box.get()); // <?> 는 <? extends Object> 와 동일하다.
    }

    public static <T extends Animal> void printWithGeneric2(Box<T> box) { // 제네릭 메서드 선언
        T t = box.get();
        System.out.println("이름 = " + t.getName());
    }

    public static void printWithWildcard2(Box<? extends Animal> box) { // 제네릭 타입을 편하게 사용하기 위해 와일드 카드 사용
        Animal animal = box.get();
        System.out.println("이름 = " + animal.getName());
    }

    public static <T extends Animal> T printAndReturnGeneric(Box<T> box) { // 제네릭 메서드 선언
        T t = box.get();
        System.out.println("이름 = " + t.getName());
        return t;
    }

    public static Animal printAndReturnWildcard(Box<? extends Animal> box) { // 제네릭 타입을 편하게 사용하기 위해 와일드 카드 사용
        Animal animal = box.get();
        System.out.println("이름 = " + animal.getName());
        return animal;
    }
}
