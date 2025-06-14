package ex1;

public class Main {

    public static void main(String[] args) {

        GenericTypeBox<Integer> integerBox = new GenericTypeBox<Integer>(); //생성 시점에 T의 타입 결정
        integerBox.setValue(10);
//        integerBox.setValue("10"); // compile error, integerBox 는 이미 타입 매개변수로 타입 인자가 Integer 로 정해졌기 때문에 String 은 컴파일 에러이다.
        Integer integerBoxValue = integerBox.getValue();
        System.out.println(integerBoxValue);

//        GenericTypeBox<int> intBox = new GenericTypeBox<int>; // compile error, 타입 인자로 primitive type 은 불가하다. Wrapper 클래스를 사용하도록 하자.

        GenericTypeBox<String> stringBox = new GenericTypeBox<>(); // 타입 추론에 의해 두번째 타입 파라미터에 들어갈 타입 인자는 생략이 가능하다

        GenericTypeBox objectBox = new GenericTypeBox(); // 타입 인자를 모두 생략이 가능한데 이 경우에는 Object 가 사용된 것으로 친다. raw type 이라 부른다.
    }
}
