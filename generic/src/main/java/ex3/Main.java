package ex3;

import animal.Animal;
import animal.Cat;
import animal.Dog;

public class Main {

    public static void main(String[] args) {

        Animal cat = GenericMethodUtil.animalMethod(new Cat("냥", 1));
        Dog dog = GenericMethodUtil.<Dog>genericMethod(new Dog("멍", 1)); // 제네릭 메서드에서도 타입 추론으로 인해 두번째 생략가능
        /**
         * 제네릭의 특성 덕분에
         * 다형성(Animal 사용) 만으로 Util 클래스를 구성했을 때.. 리턴 타입이 Animal 이 되어..
         * Dog 타입이 필요할 경우 다운 캐스팅이 필요한 상황을 없애준다.
         */


    }
}
