package sub6_operation;

import java.util.Optional;

public class OptionalOperation {

    /**
     * Optional 가 제공하는 ...
     * 값이 존재하거나 존재하지 않을 때를 분리하여 다르게 처리하기 위한 다양한 메서드를 알아본다.
     *
     * 1. ifPresent
     *      public void ifPresent(Consumer<? super T> action)
     *      내부 값이 존재하면, 파라미터로 전달된 Consumer 가 실행된다.
     *      내부 값이 존재하지 않으면, 아무것도 수행하지 않음
     * 2. ifPresentOrElse
     *      public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
     *      내부 값이 존재하면, 첫번째 파라미터로 전달된 Consumer 가 실행된다.
     *      내부 값이 존재하지 않으면, 두번째 파라미터로 전달된 Runnable 이 실행된다.
     * 3. map
     * 4. flatMap
     * 5. filter
     * 6. stream
     */

    public static void main(String[] args) {

        Optional<String> optValue = Optional.of("hello");
        Optional<String> optEmpty = Optional.empty();


        // 1. ifPresent(Consumer<? super T> action)
        optValue.ifPresent(s -> System.out.println("optValue.ifPresent(), When Optional value exists = " + s));
        optEmpty.ifPresent(s -> System.out.println("optEmpty.ifPresent(), When Optional value exists = " + s));





        // 2. ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
        optValue.ifPresentOrElse(
                s -> System.out.println("optValue.ifPresentOrElse(), When Optional value exists = " + s),
                () -> System.out.println("optValue.ifPresentOrElse(), When the optional value does not exist")
        );
        optEmpty.ifPresentOrElse(
                s -> System.out.println("optEmpty.ifPresentOrElse(), When Optional value exists = " + s),
                () -> System.out.println("optEmpty.ifPresentOrElse(), When the optional value does not exist")
        );





        // 3. map
        // 4. flatMap
        // 5. filter
        // 6. stream
    }
}
