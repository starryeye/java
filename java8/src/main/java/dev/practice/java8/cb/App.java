package dev.practice.java8.cb;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class App {

    /**
     * Stream 알아보기.. (직접 설명을 보면 잘 와 닿을 수 있다.)
     *
     * 걸러내기
     * - Filter(Predicate)
     * - 예) 이름이 3글자 이상인 데이터만 새로운 스트림으로
     *
     * 변경하기
     * - Map(Function) 또는 FlatMap(Function)
     * - 예) 각각의 Post 인스턴스에서 String title만 새로운 스트림으로
     * - 예) List<Stream<String>>을 String의 스트림으로
     *
     * 생성하기
     * - generate(Supplier) 또는 Iterate(T seed, UnaryOperator)
     * - 예) 10부터 1씩 증가하는 무제한 숫자 스트림
     * - 예) 랜덤 int 무제한 스트림
     *
     * 제한하기
     * - limit(long) 또는 skip(long)
     * - 예) 최대 5개의 요소가 담긴 스트림을 리턴한다.
     * - 예) 앞에서 3개를 뺀 나머지 스트림을 리턴한다.
     *
     * 스트림에 있는 데이터가 특정 조건을 만족하는지 확인
     * - anyMatch(), allMatch(), nonMatch()
     * - 예) k로 시작하는 문자열이 있는지 확인한다. (true 또는 false를 리턴한다.)
     * - 예) 스트림에 있는 모든 값이 10보다 작은지 확인한다.
     *
     * 개수 세기
     * - count()
     * - 예)10 보다 큰 수의 개수를 센다.
     *
     * 스트림을 데이터 하나로 뭉치기
     * - reduce(identity, BiFunction), collect(), sum(), max()
     * - 예)모든 숫자 합 구하기
     * - 예)모든 데이터를 하나의 List 또는 Set 에 옮겨담기
     */

    public static void main(String[] args) {

        Lecture java = new Lecture(1, "Java", true);
        Lecture spring = new Lecture(2, "Spring", true);
        Lecture jpa = new Lecture(3, "JPA", false);
        Lecture redis = new Lecture(4, "Redis", false);
        Lecture kafka = new Lecture(5, "Kafka", false);
        Lecture springBoot = new Lecture(6, "Spring Boot", false);
        List<Lecture> backend = List.of(java, spring, jpa, redis, kafka, springBoot);

        // --

        System.out.println("강좌 이름이 Spring 으로 시작 하는 강좌");
        backend.stream()
                .filter(lecture -> lecture.getTitle().startsWith("Spring"))
                .forEach(lecture -> System.out.println(lecture.getTitle()));

        // --

        System.out.println("close 되지 않은 강좌");
        backend.stream()
                .filter(lecture -> !lecture.isClosed()) //Predicate.not(Lecture::isClosed) 로도 가능, 임의 객체 인스턴스 메서드 레퍼런스 (Lecture::isClosed)
                .forEach(lecture -> System.out.println(lecture.getTitle()));

        // --

        System.out.println("강좌 이름만 모아서 출력");
        backend.stream()
                .map(Lecture::getTitle) // in: Lecture, out: String
                .forEach(System.out::println); // in: String, out: void

        // --

        Lecture cpp = new Lecture(7, "Cpp", true);
        Lecture modernCpp = new Lecture(8, "Modern Cpp", true);
        Lecture linux = new Lecture(9, "Linux", false);
        List<Lecture> embedded = List.of(cpp, modernCpp, linux);

        List<List<Lecture>> lists = List.of(backend, embedded);

        // --

        System.out.println("두개의 수업 목록에 들어있는 모든 수업 아이디 출력");
        // Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        Function<List<Lecture>, Stream<Lecture>> mapper = (lectureList) -> lectureList.stream();
        lists.stream() // lists 의 타입은 List<List<Lecture>>
                .flatMap(mapper) // in: List<Lecture>, out: Stream<Lecture>
                .forEach(lecture -> System.out.println(lecture.getTitle())); // in: Lecture, out: void

        // 축약
        lists.stream()
                .flatMap(Collection::stream)
                .forEach(lecture -> System.out.println(lecture.getTitle()));

        /**
         * 아래와 위의 차이점...
         * flatMap, map 둘다 동일한 함수 인터페이스의 구현체를 넣어주므로..
         * 둘다.. List<Lecture> 를 넣고 Stream<Lecture> 를 빼는데...
         * map 은 리턴 값(Stream<Lecture>)을 그대로 기존 stream 에 집어 넣는 것이고..
         * flatMap 은 리턴된 Stream<Lecture> 을 Stream 원소만 다시 추출해서 기존 stream 에 집어 넣는 듯..
         */
//        lists.stream()
//                .map(Collection::stream)
//                .forEach(lecture -> System.out.println(lecture.getTitle()));

        // --

        System.out.println("10 부터 1 씩 증가 하는 무제한 스트림 중에서 앞에 10 개를 빼고, 이후 10 개를 선택");
        Stream.iterate(10, i -> i + 1); // 이 것은.. 무한대로 증가시켜주는 스트림이다. iterate 은 중계 오퍼레이터 이다.
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        // --

        System.out.println("임베디드 강좌 이름 중에 Modern 이라는 String 이 포함 되는지 확인");
        boolean modern = embedded.stream()
                .anyMatch(lecture -> lecture.getTitle().contains("Modern"));
        System.out.println(modern);

        // --

        System.out.println("백엔드 강좌 이름 중에 Spring 이라는 String 이 포함된 강좌를 모아서 List");
        // filter 사용
        List<Lecture> springList = backend.stream()
                .filter(lecture -> lecture.getTitle().contains("Spring"))
                .toList();
        springList.forEach(lecture -> System.out.println(lecture.getTitle()));


        // --

        System.out.println("백엔드 강좌 이름 중에 Spring 이라는 String 이 포함된 강좌의 이름을 모아서 List");
        List<String> springLectureNameList = backend.stream()
                .map(Lecture::getTitle)
                .filter(title -> title.contains("Spring")) //TODO 논리적으로 map 과 filter 의 순서를 바꿔서 생각해 볼 수 있다 성능 상으로 뭐가 더 우위일까..?
                .toList();
        springLectureNameList.forEach(System.out::println); //TODO stream().forEach() 와 그냥 forEach() 차이점..?


    }
}
