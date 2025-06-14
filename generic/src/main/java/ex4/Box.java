package ex4;

public class Box<T> { // 제네릭 타입 선언

    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
