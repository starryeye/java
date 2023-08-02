package dev.practice.java8.ca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class App {
    /**
     * Stream
     * - sequence of elements supporting sequential and parallel aggregate operations
     * - 데이터를 담고 있는 저장소(컬렉션) 가 아니다.
     * - Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
     * - 스트림으로 처리 하는 데이터는 오직 한번만 처리된다.
     * - 처리되어야 할 데이터가 무제한 일 수도 있다. (Short Circuit 메서드를 사용해서 처리 데이터를 제한할 수 있다.)
     * - Stream 에서 제공하는 메서드는 중계 오퍼레이터, 종료 오퍼레이터로 크게 두 종류로 구분된다.
     * - 중계 오퍼레이션은 근본적으로 lazy 하다. (중계 오퍼레이터는 종료 오퍼레이터가 실행되기 전까지 실행되지 않는다.)
     * - 손쉽게 병렬 처리할 수있다.
     *
     * 스트림 파이프라인
     * - 0 또는 다수의 중계 오퍼레이션 (intermediate operation)과 한개의 종료 오퍼레이션 (terminal operation)으로 구성
     * - 스트림의 데이터 소스는 오직 터미널 오퍼네이션을 실행할 때에만 처리한다.
     *
     * 중계 오퍼레이터
     * - Stream 을 리턴 한다.
     * - Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다.
     * (대부분은 Stateless 지만 distinct 나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
     * - filter, map, limit, skip, sorted, ...
     *
     * 종료 오퍼레이터
     * - Stream 을 리턴하지 않는다.
     * - collect, allMatch, count, forEach, min, max, ...
     *
     * 참고
     * - https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
     * - https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
     */

    public static void main(String[] args) {

        /**
         * Stream
         * - 연속된 데이터..
         *
         * Stream API ..
         * - 연속된 데이터를 처리하는 오퍼레이션의 모음이다.
         * - 연속된 데이터들이 있으면 이를 소스로 사용해서 어떤 작업을 하는 것이다. (스트림은 컬랙션의 한 종류가 아니다.)
         * - 컨베이어벨트라 생각하자...
         * - 스트림은 Functional in nature 성격을 가진다. -> 스트림에서 소스로 사용된 데이터는 변경되지 않는다. (스트림에서 변한것)
         * - Stream 에서 제공하는 메서드는 중계 오퍼레이터, 종료 오퍼레이터로 크게 두 종류로 구분된다.
         * - parallelStream 으로 병렬적으로 스트림을 처리할 수 있다.
         *
         * 중계 오퍼레이터
         * - 중계 오퍼레이션은 근본적으로 lazy 하다. (중계 오퍼레이터는 종료 오퍼레이터가 실행되기 전까지 실행되지 않는다.)
         * - 하나의 스트림에 여러개의 중계 오퍼레이터가 존재할 수 있다.
         * - 여러개의 중계 오퍼레이터를 메서드 체이닝 방식으로 적용할 수 있다. (중계 오퍼레이터는 리턴 타입이 Stream 이다.)
         * - filter, map, limit, skip, sorted, ...
         *
         * 종료 오퍼레이터
         * - Stream 을 리턴하지 않는다.
         * - collect, allMatch, count, forEach, min, max, ...
         *
         * 스트림 파이프라인
         * - 여러개의 중계 오퍼레이터와 하나의 종료 오퍼레이터로 구성된 완전한 스트림을 스트림 파이프라인이라 부른다.
         *
         */

        List<String> names = new ArrayList<>();
        names.add("Java");
        names.add("Spring");
        names.add("JPA");
        names.add("Redis");

        // Functional in nature
        Stream<String> stringStream = names.stream()
                .map(String::toUpperCase);
        names.forEach(System.out::println); // Functional in nature, 원본은 변경 되지 않았다.

        System.out.println("===");

        // 중계 오퍼레이터는 lazy 하다.
        Stream<String> stringStream1 = names.stream()
                .map((s) -> {
                    System.out.println(s); // map 은 중계 오퍼레이터이다. 해당 stream 에는 종료 오퍼레이터가 없으므로 map 은 수행되지 않는다.
                    return s.toUpperCase();
                });
        System.out.println("===");
        stringStream1.forEach(System.out::println); // stream 에 종료 오퍼레이터를 수행하도록 하여 이전의 중계 오퍼레이터를 수행한다.

        System.out.println("===");

        // 일반적인 for, while 등으로 구현된 loof 는 병렬적으로 처리하기가 어렵다.
        // stream 을 활용하면 손쉽게 병렬처리를 할 수 있다. (JVM 이 알아서 한다. ForkJoinPool 사용..)
        // 참고, String::toUpperCase 는 임의 객체 인스턴스 메서트 레퍼런스이다.
        List<String> list = names.parallelStream() // 병렬 스트림
                .map((s) -> {
                    System.out.println(s + ", Tx = " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).toList();
        list.forEach(System.out::println);
    }
}
