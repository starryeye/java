package stream.sub2_create_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateStream {

    /**
     * Stream 을 생성하는 방법에 대해 알아본다.
     *
     * 1. Collection 으로 부터 Stream 생성
     * 2. 배열로 부터 Stream 생성
     * 3. 직접 요소를 파라미터로 전달하여 Stream 생성
     * 4. 무한 Stream 생성
     *      - iterate() : 초깃값, lambda 전달
     *      - generate() : Supplier 전달
     */
    public static void main(String[] args) {

        System.out.println("1. 컬렉션으로부터 생성");
        List<String> list = List.of("a", "b", "c");
        Stream<String> stream1 = list.stream();
        stream1.forEach(System.out::println);


        System.out.println("2. 배열로부터 생성");
        String[] arr = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(arr);
        stream2.forEach(System.out::println);


        System.out.println("3. Stream.of() 사용");
        Stream<String> stream3 = Stream.of("a", "b", "c");
        stream3.forEach(System.out::println);


        System.out.println("4-1. 무한 스트림 생성 - iterate()");
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2); // iterate() : 초기값과 다음 값을 만드는 함수를 지정
        // 무한이라 출력은 따로 안함

        System.out.println("4-2. 무한 스트림 생성 - generate()");
        Stream<Double> randomStream = Stream.generate(Math::random); // generate() : Supplier를 사용하여 무한하게 생성
        // 무한이라 출력은 따로 안함
    }
}
