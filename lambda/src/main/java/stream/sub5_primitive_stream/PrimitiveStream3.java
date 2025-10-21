package stream.sub5_primitive_stream;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStream3 {

    /**
     * 기본형 특화 스트림이 제공하는 타입 변환과 일반 스트림과의 변환을 알아본다.
     *
     * asXXXStream
     *      asIntStream, asLongStream, asDoubleStream
     *      기본형 특화 스트림에서 다른 타입의 기본형 특화 스트림으로 변환할 수 있다.
     *          IntStream 은 LongStream, DoubleStream 가능
     *          LongStream 은 DoubleStream 가능
     * boxed
     *      boxed
     *      기본형 특화 스트림의 요소들을 boxing 하여 일반 스트림으로 변환할 수 있다.
     * mapToXXX
     *      mapToInt, mapToLong, mapToDouble, mapToObj
     *      일반 스트림과 기본형 특화 스트림간의 쌍방 변환을 할 수 있다.
     */

    public static void main(String[] args) {

        // asXXXStream
        System.out.println("=== asXXXStream Example ===");
        DoubleStream doubleStream = IntStream.of(1, 2, 3, 4, 5)
                .asDoubleStream();
        doubleStream.forEach(n -> System.out.print(n + " "));
        System.out.println();


        // boxed
        System.out.println("=== boxed Example ===");
        Stream<Integer> boxedStream = IntStream.of(10, 20, 30)
                .boxed();
        boxedStream.forEach(n -> System.out.print(n + " "));


        // mapToXXX
        System.out.println("=== mapToXXX Example ===");

        // Stream<String> to IntStream
        List<String> strings1 = List.of("1", "2", "3", "4");
        IntStream mappedIntStream = strings1.stream()
                .mapToInt(Integer::parseInt);
        System.out.println("Sum of mapped IntStream: " + mappedIntStream.sum());

        // Stream<String> to DoubleStream
        List<String> strings2 = List.of("11", "22", "33", "44");
        DoubleStream mappedDoubleStream = strings2.stream()
                .mapToDouble(Double::parseDouble);
        System.out.println("Average of mapped DoubleStream: " + mappedDoubleStream.average().orElse(0.0));

        // IntStream to Stream<String> using mapToObj
        IntStream intStream3 = IntStream.of(5, 6, 7);
        Stream<String> stream = intStream3.mapToObj(num -> "Value: " + num);
        stream.forEach(System.out::println);



    }
}
