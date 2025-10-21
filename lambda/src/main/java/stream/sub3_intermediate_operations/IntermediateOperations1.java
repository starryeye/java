package stream.sub3_intermediate_operations;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperations1 {

    /**
     * 중간 연산자에 대해 알아본다.
     *
     * 1. filter
     *      Stream<T> filter(Predicate<? super T> predicate);
     *          조건에 맞는 요소만 추출한다.
     * 2. map
     *      <R> Stream<R> map(Function<? super T, ? extends R> mapper);
     *          요소를 다른 형태로 변환한다.
     *          참고로 mapToXXX 와 같은 연산자가 있는데 sub5 의 PrimitiveStream3 를 보자
     * 3. distinct
     *      Stream<T> distinct();
     *          중복 요소를 제거한다. (Object.equals(Object))
     * 4. sorted
     *      Stream<T> sorted();
     *          Stream 의 요소를 정렬한다. (natural ordering)
     *          해당 Stream 이 Comparable 구현되지 않았다면, ClassCastException 발생한다.
     *              즉, Stream 구현체마다 Comparable 을 구현하여 sorted 호출 시 이를 이용한다.
     *      Stream<T> sorted(Comparator<? super T> comparator);
     *          제공된 Comparator 를 통해 Stream 의 요소를 정렬한다.
     * 5. peek
     *      Stream<T> peek(Consumer<? super T> action);
     *          중간 단계에서 엿보는(peek) 용도로 사용된다.
     *          Consumer 를 파라미터로 받고 파이프라인 관점에서는 데이터가 변경되지 않는다(i -> i)
     * 6. limit
     *      Stream<T> limit(long maxSize);
     *          최초 요소 부터 maxSize 개의 요소까지만 추출한다.
     * 7. skip
     *      Stream<T> skip(long n);
     *          최초 요소 부터 n 개의 요소를 건너뛰고 나머지 요소들을 추출한다.
     * 8. takeWhile (Java 9)
     *      default Stream<T> takeWhile(Predicate<? super T> predicate)
     *          최초 요소 부터 조건이 처음으로 거짓이 되는 요소 직전까지를 추출한다.
     *              정렬된 Stream 일 경우 filter 로도 동일한 결과를 만들어 낼 수 있지만..
     *              filter 는 모든 요소를 다 검사하는데에 비해 takeWhile 은 조건이 처음으로 거짓이 되면 실행을 멈춘다 (성능 이점)
     * 9. dropWhile (Java 9)
     *      default Stream<T> dropWhile(Predicate<? super T> predicate)
     *          최초 요소 부터 조건이 처음으로 거짓이 되는 요소 직전까지는 버리고 그 이후 요소들만 추출한다.
     *              정렬된 Stream 일 경우 filter 로도 동일한 결과를 만들어 낼 수 있지만..
     *              filter 는 모든 요소를 다 검사하는데에 비해 takeWhile 은 조건이 처음으로 거짓이 되면 실행을 멈춘다 (성능 이점)
     */

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        System.out.println("stream = " + stream.getClass());
        System.out.println("This Stream is instanceof Comparable = " + (stream instanceof Comparable));

        // filter
        System.out.println("1. filter - 짝수만 선택");
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();


        // map
        System.out.println("2. map - 각 요소 제곱");
        numbers.stream()
                .map(i -> i * i)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();


        // distinct
        System.out.println("3. distinct - 중복 제거");
        numbers.stream()
                .distinct()
                .forEach(i -> System.out.print(i + " "));
        System.out.println();


        // sorted
        System.out.println("4-1. sorted - 기본 정렬, natural sorting");
        numbers.stream()
                .sorted()
                .forEach(i -> System.out.print(i + " "));
        System.out.println();


        // sorted
        System.out.println("4-2. sorted with Comparator - 내림차순 정렬");
        numbers.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(i -> System.out.print(i + " "));
        System.out.println();


        // peek
        System.out.println("5. peek - doOnNext 비슷");
        numbers.stream()
                .peek(i -> System.out.println("peek() just before map(), i = " + i + ", "))
                .map(i -> i * i)
                .peek(i -> System.out.println("peek() after map(), i = " + i + ", "))
                .limit(5)
                .forEach(i -> System.out.println("result = " + i));


        // limit
        System.out.println("6. limit - 최초 5 개 요소만");
        numbers.stream()
                .limit(5)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();


        // skip
        System.out.println("7. skip - 최초 5 개 요소는 건너뛰기");
        numbers.stream()
                .skip(5)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();



        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

        // takeWhile
        System.out.println("8. takeWhile - 최초 5 보다 작은 요소만");
        numbers2.stream()
                .takeWhile(i -> i < 5)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();


        // dropWhile
        System.out.println("9. dropWhile - 최초 5 보다 작은 요소는 건너뛰기");
        numbers2.stream()
                .dropWhile(i -> i < 5)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
