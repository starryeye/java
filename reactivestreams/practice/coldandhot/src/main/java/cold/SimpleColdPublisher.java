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
     * - subscriber 에 따라 독립적인 데이터 스트림 제공한다.
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
