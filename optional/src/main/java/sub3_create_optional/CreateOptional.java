package sub3_create_optional;

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

    }
}
