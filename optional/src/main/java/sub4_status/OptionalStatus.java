package sub4_status;

import java.util.Optional;

public class OptionalStatus {

    /**
     * Optional 내부 값이 존재하는지 여부를 체크할 수 있다.
     *
     * isPresent
     * isEmpty
     */

    public static void main(String[] args) {

        Optional<String> opt1 = Optional.of("Hello");
        Optional<Object> opt2 = Optional.empty();


        // isPresent
        System.out.println("opt1.isPresent() : " + opt1.isPresent());
        System.out.println("opt2.isPresent() : " + opt2.isPresent());


        // isEmpty
        System.out.println("opt1.isEmpty() : " + opt1.isEmpty());
        System.out.println("opt2.isEmpty() : " + opt2.isEmpty());
    }
}
