package sub7_operation;

import java.util.Optional;
import java.util.stream.Stream;

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
     *      public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
     *      내부 값이 존재하면, 파라미터로 전달된 Function 이 실행되고, 그 결과물이 Optional 내부 값으로 적용된다.
     *          내부 값이 존재해서 파라미터로 전달된 Function 이 실행 되었지만, 그 결과물이 null 이면 Optional.empty() 반환이다.
     *      내부 값이 존재하지 않으면, Optional.empty() 가 반환된다.
     * 4. flatMap
     *      public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)
     *      Optional<Optional<String>> 타입과 같이 중첩 Optional 타입인 경우
     *      Optional<String>> 으로 평탄화 해준다. (그리고 map 처럼 Function 을 수행)
     * 5. filter
     *      public Optional<T> filter(Predicate<? super T> predicate)
     *      내부 값이 존재하고 파라미터로 전달된 Predicate 를 만족하면, 통과되어 Optional<T> 가 반환
     *      내부 값이 존재하고 파라미터로 전달된 Predicate 를 불만족하면, Optional.empty 반환
     *      내부 값이 존재하지 않으면, Optional.empty 반환
     * 6. stream
     *      public Stream<T> stream()
     *      내부 값이 존재하면, 단일 요소 Stream 반환 (여기서 단일 요소는 Optional 의 내부 값이다.)
     *      내부 값이 존재하지 않으면, 요소가 없는 Stream 반환되어 Stream 으로 뭘해도 실행안됨.
     *
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





        // 3. map(Function<? super T, ? extends U> mapper)
        Optional<String> mappedOpt1 = optValue.map(String::toUpperCase);
        System.out.println("optValue.map() = " + mappedOpt1);
        Optional<String> mappedOpt2 = optEmpty.map(String::toUpperCase);
        System.out.println("optEmpty.map() = " + mappedOpt2);





        // 4. flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)
        Optional<Optional<String>> optOptValue = Optional.of(Optional.of("hello"));
        Optional<String> flattenedOptValue = optOptValue.flatMap(v -> v);
        System.out.println("optOptValue.flatMap(), flattenedOptValue = " + flattenedOptValue);




        // 5. filter(Predicate<? super T> predicate)
        Optional<String> filteredOptValue1 = optValue.filter(s -> s.startsWith("h"));
        System.out.println("optValue.filter(), filteredOptValue1 = " + filteredOptValue1);
        Optional<String> filteredOptValue2 = optValue.filter(s -> s.startsWith("x"));
        System.out.println("optValue.filter(), filteredOptValue2 = " + filteredOptValue2);
        Optional<String> filteredOptValue3 = optEmpty.filter(s -> s.startsWith("h"));
        System.out.println("optEmpty.filter(), filteredOptValue3 = " + filteredOptValue3);



        // 6. stream
        Stream<String> optValueStream = optValue.stream();
        System.out.println("optValueStream = " + optValueStream);
        optValueStream.forEach(s -> System.out.println("optValue.stream(), s = " + s));
        Stream<String> optEmptyStream = optEmpty.stream();
        System.out.println("optEmptyStream = " + optEmptyStream);
        optEmptyStream.forEach(s -> System.out.println("optEmpty.stream(), s = " + s));
    }
}
