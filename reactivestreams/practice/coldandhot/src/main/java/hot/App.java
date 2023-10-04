package hot;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    @SneakyThrows
    public static void main(String[] args) {
        // publisher 생성
        var publisher = new SimpleHotPublisher();

        // subscriber 1 번 생성
        var subscriber1 = new SimpleNamedSubscriber<>("subscriber 1");

        publisher.subscribe(subscriber1);

        Thread.sleep(5000); // 5초 대기
        subscriber1.cancel(); // subscriber 1번 더이상 데이터 받지 않음을 알림

        // subscriber 2, 3 번 생성
        var subscriber2 = new SimpleNamedSubscriber<>("subscriber 2");
        var subscriber3 = new SimpleNamedSubscriber<>("subscriber 3");
        publisher.subscribe(subscriber2);
        publisher.subscribe(subscriber3);

        Thread.sleep(5000); // 5초 대기
        subscriber2.cancel(); // subscriber 2번 더이상 데이터 받지 않음을 알림
        subscriber3.cancel(); // subscriber 3번 더이상 데이터 받지 않음을 알림


        Thread.sleep(1000); // 1초 대기

        var subscriber4 = new SimpleNamedSubscriber<>("subscriber 4");

        publisher.subscribe(subscriber4); // 2, 3 번과 4 번 사이의 데이터도 받는다.

        Thread.sleep(5000); // 5초 대기
        subscriber4.cancel(); // subscriber 4번 더이상 데이터 받지 않음을 알림

        // shutdown publisher
        publisher.shutdown();
    }
}
