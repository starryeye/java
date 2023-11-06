package cold;

import java.util.Collections;
import java.util.concurrent.Flow;
import java.util.stream.IntStream;

public class SimpleColdPublisher implements Flow.Publisher<Integer> {

    /**
     * publisher 의 subscribe 메서드를 통해서...
     * - subscriber 를 등록할 수 있다.
     * - subscriber 에게 subscription 을 제공해준다.
     *
     * Cold Publisher
     * - subscribe 를 한 순간 부터 데이터를 생성하고 전송한다.
     * - subscribe 할 때 마다 동일한 데이터가 전송됨을 보장한다. (파일 읽기, 웹 API 요청 등에 쓰인다.)
     * - subscriber 에 따라 독립적인 데이터 스트림을 제공한다.
     *
     * 추가 개념 잡기
     * Cold Publisher 는..
     * - 구독자가 구독을 시작할 때까지 데이터를 발행하지 않는다.
     * 즉, 구독자가 명시적으로 구독을 요청해야 데이터 스트림이 시작된다.
     * - 각 구독자는 대개 고유한 데이터 스트림을 받게 된다.
     * 이는 동일한 데이터가 여러 구독자에게 동시에 전달되지 않음을 의미한다.
     * - 파일 읽기, 데이터베이스 쿼리 결과 반환 등이 Cold Publisher 의 일반적인 예이다.
     * 요청이 있을 때만 데이터를 '생성'하고, 각 구독자는 처음부터 끝까지 전체 데이터 시퀀스를 받는다.
     */
    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {

        // 전달할 데이터
        var iterator = Collections.synchronizedList(
                IntStream.range(1, 10).boxed().toList()
        ).iterator();

        // subscription 생성
        var subscription = new SimpleColdSubscription(iterator, subscriber);

        // subscriber 의 onSubscribe 메서드를 통해서 publisher 가 subscriber 에게 subscription 을 전달 해준다.
        subscriber.onSubscribe(subscription);
    }


}
