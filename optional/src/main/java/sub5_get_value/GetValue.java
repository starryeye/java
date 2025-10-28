package sub5_get_value;

import java.util.Optional;

public class GetValue {

    /**
     * Optional 내부 값을 획득하는 여러 메서드에 대해 알아본다.
     *
     *
     * 1. get
     *      public T get()
     *      내부 값을 꺼낼 수 있다.
     *      만약 내부 값이 empty 라면 NoSuchElementException 예외 발생함
     * 2. orElse
     *      public T orElse(T other)
     *      내부 값을 꺼낼 수 있다.
     *      만약 내부 값이 empty 라면 파라미터로 전달된 값이 반환된다.
     *          orElseGet 과 비교하여 즉시 평가
     * 3. orElseGet
     *      public T orElseGet(Supplier<? extends T> supplier)
     *      내부 값을 꺼낼 수 있다.
     *      만약 내부 값이 empty 라면 파라미터로 전달된 supplier 가 실행되고 그 결과 값이 반환된다.
     *          orElse 와 비교하여 지연 평가
     * 4. orElseThrow
     *      public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)
     *      내부 값을 꺼낼 수 있다.
     *      만약 내부 값이 empty 라면 파라미터로 전달된 supplier 가 실행되고 그 예외가 발생된다.
     *          Supplier 전달이므로 지연 평가이다.
     * 5. or
     *      public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier)
     *      내부 값을 꺼내는건 아니고...
     *          내부 값이 있으면, Optional 그대로 반환된다.
     *          내부 값이 없으면, 파라미터로 전달한 Supplier 가 실행되고 그 Optional 이 반환된다.
     *              Supplier 전달이므로 지연 평가이다.
     *
     * 참고
     * get() 메서드는 사용을 피하도록하자.
     * 값이 없는데 호출하는 순간 예외가 발생하므로 isPresent 로 미리 검증을 해야하는 상황에 놓이게되고..
     * 이는 Optional 을 사용하지 않을 때 null check 하는 것과 다를 바가 없어 진다.
     */

    public static void main(String[] args) {

        // given
        Optional<String> optValue = Optional.of("Hello");
        Optional<String> optEmpty = Optional.empty();



        // 1. get()
        System.out.println("optValue.get() = " + optValue.get());
//        System.out.println("optEmpty.get() = " + optEmpty.get()); // NoSuchElementException 발생



        // 2. orElse(T other)
        System.out.println("optValue.orElse(\"default\") = " + optValue.orElse("default"));
        System.out.println("optEmpty.orElse(\"default\") = " + optEmpty.orElse("default"));



        // 3. orElseGet(Supplier<? extends T> supplier)
        System.out.println("optValue.orElseGet(() -> \"default\") = " + optValue.orElseGet(() -> "default"));
        System.out.println("optEmpty.orElseGet(() -> \"default\") = " + optEmpty.orElseGet(() -> "default"));



        // 4. orElseThrow(Supplier<? extends X> exceptionSupplier)
        System.out.println("optValue.orElseThrow() = " + optValue.orElseThrow(() -> new RuntimeException("default")));
        try {
            System.out.println("optEmpty.orElseThrow() = " + optEmpty.orElseThrow(() -> new RuntimeException("optEmpty Optional is empty")));
        } catch (RuntimeException e) {
            System.out.println("optEmpty.orElseThrow().. Exception occured... message = " + e.getMessage());
        }



        // 5. or(Supplier<? extends Optional<? extends T>> supplier)
        System.out.println("optValue.or() = " + optValue.or(() -> Optional.of("fallback")));
        System.out.println("optEmpty.or() = " + optEmpty.or(() -> Optional.of("fallback")));
    }
}
