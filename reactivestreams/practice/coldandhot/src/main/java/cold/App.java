package cold;

import lombok.SneakyThrows;

public class App {

    @SneakyThrows
    public static void main(String[] args) {

        // publisher 생성
        SimpleColdPublisher publisher = new SimpleColdPublisher();

        // 1번 subscriber 생성
        SimpleNamedSubscriber<Integer> subscriber1 = new SimpleNamedSubscriber<Integer>("subscriber 1");

        // publisher 에 1번 subscriber 를 등록한다.
        publisher.subscribe(subscriber1);

        Thread.sleep(5000);

        // 2번 subscriber 생성
        SimpleNamedSubscriber<Integer> subscriber2 = new SimpleNamedSubscriber<Integer>("subscriber 2");

        // publisher 에 2번 subscriber 를 등록한다.
        publisher.subscribe(subscriber2);
    }
}
