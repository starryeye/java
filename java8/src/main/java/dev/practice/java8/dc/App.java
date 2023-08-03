package dev.practice.java8.dc;

import java.util.List;
import java.util.Optional;

public class App {

    /**
     * Optional 만들기
     * - Optional.of()
     * - Optional.ofNullable()
     * - Optional.empty()
     *
     * Optional 에 값이 있는지 없는지 확인하기
     * - isPresent()
     * - isEmpty() (Java 11부터 제공)
     *
     * Optional 에 있는 값 가져오기
     * - get()
     * - 만약에 비어있는 Optional에서 무언가를 꺼낸다면??
     *
     * Optional 에 값이 있는 경우에 그 값을 가지고 ~~를 하라.
     * - ifPresent(Consumer)
     * - 예) Spring으로 시작하는 수업이 있으면 id를 출력하라.
     *
     * Optional 에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라.
     * - orElse(T)
     * - 예) JPA로 시작하는 수업이 없다면 비어있는 수업을 리턴하라.
     *
     * Optional 에 값이 있으면 가져오고 없는 경우에 ~~를 하라.
     * - orElseGet(Supplier)
     * - 예) JPA로 시작하는 수업이 없다면 새로 만들어서 리턴하라.
     *
     * Optional 에 값이 있으면 가졌고 없는 경우 에러를 던져라.
     * - orElseThrow()
     *
     * Optional 에 들어있는 값 걸러내기
     * - Optional filter(Predicate)
     *
     * Optional 에 들어있는 값 변환하기
     * - Optional map(Function)
     * - Optional flatMap(Function): Optional 안에 들어있는 인스턴스가 Optional 인 경우에 사용하면 편리하다.
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

        //stream 에서도 종료 오퍼레이터 중에 Optional 을 리턴해주는게 있다.
        Optional<Lecture> optional = backend.stream()
                .filter(lecture -> lecture.getTitle().startsWith("Spring"))
                .findFirst();

        // --

        // Optional 내부에 값이 있는지 없는지 검사를 할 수 있다. <-> isEmpty()
        boolean present = optional.isPresent();

        // --

        // Optional 내부의 값을 꺼내 올 수 있다.
        Lecture lecture = optional.get();

        // --
        // Optional 내부에 값이 없는데 get 으로 꺼낸다면..
        Optional<Lecture> empty = Optional.empty();
//        Lecture getEmpty = empty.get(); // 런타임예와, NoSuchElementException 이 발생한다.

        // --
        // Optional 내부에 값이 있으면 어떤 작업을 수행해야 한다면.. (Consumer)
        // ifPresent 를 사용하자. (값이 없으면 수행자체를 안한다.)
        optional.ifPresent(lecture1 -> System.out.println(lecture1.getTitle()));

        //--
        // Optional 내부에 값이 있으면 꺼내고 없으면 정의한 인스턴스를 리턴해라..
        // - 근데 optional 에 값이 존재해도.. createNewLecture() 는 수행이 된다!!
        // 따라서 orElse 는 이미 상수 인스턴스로 만들어 져있는 것을 그대로 리턴하면 될때 사용하면 된다. 그 외에는 orElseGet 을 사용하면 된다.
        Lecture springLecture = empty.orElse(createNewLecture()); // orElse(T t)

        // --
        // Optional 내부에 값이 있으면 꺼내고 없으면 정의한 인스턴스를 리턴해라..
        // - optional 에 값이 존재하면 createNewLecture 수행안됨
        Lecture lecture1 = empty.orElseGet(App::createNewLecture); // orElseGet(Supplier)

        // --
        // Optional 내부에 값이 있으면 꺼내고 없으면 예외를 발생시켜라..
        Lecture lecture2 = empty.orElseThrow(); // 기본적으로는 NoSuchElementException 이나.. 파라미터(Supplier)로 원하는 예외를 정의할 수 있다.

        // --
        // Optional 에 stream 처럼 filter 를 적용할 수 있다.
        // 있으면 Optional 로 감싸진 값 , 없으면 Optional.empty 이다.
        Optional<Lecture> lecture3 = optional.filter(l -> l.getId() > 100);
        System.out.println(lecture3.isEmpty());

        // --
        // Optional 에 map 을 적용할 수 있다.
        // 역시나 Optional 로 감싸져서 리턴된다.
        Optional<Integer> getId = optional.map(Lecture::getId);

        // --
        // Optional 에 flatmap 도 존재한다.
        // Optional<Optional<?>> 일 경우 주로 사용한다.
        // optioanl 은 Optional<Lecture> 이고.. optional.map(Lecture::getProgress) 를 하면 리턴이.. Optional<Optional<Progress>> 이므로
        // flatmap 을 사용하여 Optional<Progress> 가 되도록한다.
        Optional<Progress> progress = optional.flatMap(Lecture::getProgress);
        Optional<Optional<Progress>> progress1 = optional.map(Lecture::getProgress);
    }

    //이 메서드가 static 인 이유는 main 메서드 때문이다.
    private static Lecture createNewLecture() {

        System.out.println("Create new Lecture");
        return new Lecture(10, "new lecture", true);
    }
}
