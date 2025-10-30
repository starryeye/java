package stream.sub1_description;

import java.util.List;

public class Description {

    /**
     * Java 가 제공하는 Stream API 에 대해 알아본다.
     *
     * Stream 은 Java 8 부터 추가된 기능으로, 데이터의 흐름을 추상화해서 다루는 도구이다.
     * Collection 또는 배열 등의 요소들을 연산 파이프라인을 통해 선형적인(단계별) 파이프라인 형태로 처리할 수 있게 해준다.
     *
     * 특징
     *      0. 작업을 어떻게(How) 수행해야 하는지 보다 무엇(What)을 수행해야 하는지로 코드가 짜여진다.
     *          Lambda 와 Method Reference 를 적극 활용하여 선언적 프로그래밍 방식의 특징을 가짐
     *      1. 데이터 소스(원본)을 변경하지 않음(Immutable)
     *          Stream은 데이터 원본(Collection 등)을 변경하지 않으며, 원본 데이터를 복사하거나 새 값을 생성해 반환
     *      2. 1회성
     *          Stream 을 한번 연산시키고 2회째 연산을 수행시키면 예외 발생(IllegalStateException)
     *          필요하다면, Stream 을 새로 생성해서 사용
     *      3. 파이프라인 구성
     *          Stream은 여러개의 중간 연산(intermediate operation)과 최종 연산(terminal operation)으로 구성
     *      4. 지연 연산
     *          {1, 2, 3} 데이터에 대해 중간 연산 a, b 가 있고 최종 연산 c 를 수행한다면..
     *          각 데이터 요소 단위로 파이프라인(a, b, c)을 따라 흐른다
     *              모든 데이터 요소가 a 를 수행하고 다시 모든 데이터 요소가 b 를 수행하는 방식이 아님.
     *          최종 연산 없이 중간 연산만 존재하는 Stream 은 실행되지 않음.
     *      5. 병렬 처리
     *          멀티 코어 환경에서 Stream 연산을 병렬로 수행하는 코드를 간단하게 작성할 수 있다.
     *              parallelStream()을 사용하면 내부적으로 ForkJoinPool을 이용해 멀티코어 환경에서 병렬 연산을 수행
     *      6. 3번 4번 특징으로 인한 최적화
     *          단축 평가(short-circuit) 최적화가 가능하다.
     *          "필요한 시점에만 데이터를 파이프라인을 통해 흘려보내며 연산을 수행하고, 필요 이상으로 처리하지 않는다."
     *
     * 참고.
     * Stream 은 파이프라인 구성, 지연 연산의 특징으로 인해 단축 평가와 같은 최적화가 가능하다.
     * -> 아래 코드에서 설명
     *
     * 참고.
     * lambda.sub6_stream.sub3_stream_v1; 에서 만들어본 MyStreamV1 은 즉시 연산의 특징을 가지고 있다.
     */

    public static void main(String[] args) {

        List<Integer> data = List.of(1, 2, 3, 4, 5, 6);

        data.stream()
                .filter(i -> {
                    boolean isEven = i % 2 == 0;
                    System.out.println("filter() 실행 됨. i = " + i);
                    return isEven;
                })
                .map(i -> {
                    int mapped = i * 10;
                    System.out.println("map() 실행됨. mapped = " + mapped);
                    return mapped;
                })
                .findFirst()
                .ifPresent(result -> System.out.println("result = " + result));

        /**
         * 실행 해보면.. 데이터 1 에 대해 filter() 가 수행되고
         * 데이터 2 에 대해 filter(), map(), findFirst(), ifPresent() 가 수행되고
         * 이후 데이터 3 ~ 6 은 수행되지않고 Stream 연산이 종료된다.
         * -> 조건을 만족하는 결과를 찾으면 더 이상 연산을 진행하지 않는다. (단축 평가, short-circuit)
         *      파이프라인 방식, 지연 연산 특징이 있어서 이런 최적화가 가능한 것이다.
         */
    }
}
