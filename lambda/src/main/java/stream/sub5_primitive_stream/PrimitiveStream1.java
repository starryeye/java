package stream.sub5_primitive_stream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class PrimitiveStream1 {

    /**
     * 기본형 특화 스트림에 대해 알아본다.
     *
     * 의의
     *      Java 제네릭은 primitive type 지원을 하지 않기 때문에 primitive type 을 다루기 위해서는..
     *      wrapper type + Stream 을 사용해야한다.
     *      하지만, wrapper type 을 다루면 아무래도 auto boxing / unboxing 으로 인한 성능 손실이 발생함..
     *      그래서 기본형 특화 스트림을 제공한다.
     *
     * Java 에서 제공하는 primitive type 중..
     * int, long, double 에 대해 IntStream, LongStream, DoubleStream 을 제공한다.
     *
     * 기본형 특화 스트림은..
     *      타입이 고정되기 때문에 auto boxing / unboxing 에 대한 성능 향상 뿐만아니라
     *      합계, 평균, 최솟값, 최댓값 등 기본 연산과
     *      범위, 타입 변환 관련 기능도 함께 제공한다.
     *
     *
     *
     *
     * 기본형 특화 스트림 생성
     * 1. of
     *      public static IntStream of(int... values)
     *      위 IntStream 뿐만 아니라 LongStream, DoubleStream 에서도 제공한다.
     *
     * 2. range
     *      public static IntStream range(int startInclusive, int endExclusive)
     *      public static IntStream rangeClosed(int startInclusive, int endInclusive)
     *      위 IntStream 뿐만 아니라 LongStream 에서도 제공한다.
     *      range 는 startInclusive <= 요소 < endExclusive 로 요소가 생성되고
     *      rangeClosed 는 startInclusive <= 요소 <= endInclusive 로 요소가 생성된다.
     */

    public static void main(String[] args) {

        // of
        IntStream stream1 = IntStream.of(1, 2, 3, 4, 5);
        stream1.forEach(n -> System.out.print(n + " "));
        System.out.println();

        LongStream stream2 = LongStream.of(1, 2, 3, 4, 5);
        stream2.forEach(n -> System.out.print(n + " "));
        System.out.println();

        DoubleStream stream3 = DoubleStream.of(1, 2, 3, 4, 5);
        stream3.forEach(n -> System.out.print(n + " "));
        System.out.println();



        // range
        IntStream range1 = IntStream.range(1, 6);
        range1.forEach(n -> System.out.print(n + " "));
        System.out.println();

        IntStream range2 = IntStream.rangeClosed(1, 6);
        range2.forEach(n -> System.out.print(n + " "));
        System.out.println();

        LongStream range3 = LongStream.range(1, 6);
        range3.forEach(n -> System.out.print(n + " "));
        System.out.println();

        LongStream range4 = LongStream.rangeClosed(1, 6);
        range4.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
