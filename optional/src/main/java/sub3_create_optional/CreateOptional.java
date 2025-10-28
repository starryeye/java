package sub3_create_optional;

import java.util.Optional;

public class CreateOptional {

    /**
     * Optional 을 생성하는 방법
     *
     * 1. Optional.of(T value)
     *      내부 값이 절대 null 이 아닐 때 사용한다. (null 을 넣으면 NPE 발생함)
     * 2. Optional.ofNullable(T value)
     *      내부 값이 null 일 수도, 아닐 수도 있을 때 사용한다.
     *      Optional.ofNullable(null) 은 Optional.empty() 와 동일하다.
     * 3. Optional.empty()
     *      내부 값이 null 일 때 사용한다.
     */

    public static void main(String[] args) {

        // of : 값이 절대 null 이 아닐때 사용
        Optional<String> opt1 = Optional.of("Hello");
        System.out.println("opt1 = " + opt1);


        // ofNullable : 값이 null 일 수도, 값이 존재할 수도
        Optional<String> opt2 = Optional.ofNullable(null);
        Optional<String> opt3 = Optional.ofNullable("World");
        System.out.println("opt2 = " + opt2);
        System.out.println("opt3 = " + opt3);


        // empty : 값이 null 일때 사용
        Optional<Object> opt4 = Optional.empty();
        System.out.println("opt4 = " + opt4);
    }
}
