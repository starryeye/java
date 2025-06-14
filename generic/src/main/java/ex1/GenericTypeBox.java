package ex1;

public class GenericTypeBox<T> {

    /**
     * 용어
     *      GenericTypeBox<T> 는 제네릭 타입(generic type)이다.
     *      T 는 타입 매개변수(type parameter)이다.
     *      실행 시점에 정해지는 T 에 들어갈 레퍼런스 타입은 타입 인자(type argument)이다.
     *
     * 참고
     * 메서드는 매개변수에 인자를 전달해서 사용할 "값을 결정"한다.
     * 제네릭 타입은 타입 매개변수에 타입 인자를 전달해서 사용할 "타입을 결정"한다.
     */

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
