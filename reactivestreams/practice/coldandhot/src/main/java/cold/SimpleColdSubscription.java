package cold;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

@RequiredArgsConstructor
public class SimpleColdSubscription implements Flow.Subscription {

    /**
     * publisher 가 subscriber 에게 제공해주는 데이터 흐름 조절 리모콘이다.
     *
     * request 메서드 : subscriber 가 몇개의 요청(데이터)을 받을 건지 publisher 에게 알리는 용도 (back-pressure 조절)
     *
     * cancel 메서드 : subscriber 가 더이상 요청(데이터)을 받지 않겠다고 publisher 에게 알리는 용도
     */

    private final Iterator<Integer> iterator;
    private final Flow.Subscriber<? super Integer> subscriber;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor(); // 별도의 스레드

    @Override
    public void request(long n) {

        // 원래 아래 로직은 publisher 의 역할이다.
        // request 메서드는 subscriber 의 스레드로 동작한다.
        // publisher 와 subscriber 는 비동기적으로 동작하므로.. 별도의 스레드로 동작하도록 하였다.
        // 파라미터 n 은 몇개의 데이터를 받을 건지 이다.
        executorService.submit(() -> {
            for (int i = 0; i < n; i++) {
                if (iterator.hasNext()) {
                    Integer number = iterator.next();
                    iterator.remove();
                    subscriber.onNext(number); // subscriber 의 onNext 메서드를 호출하여 데이터를 push 해준다.
                } else {
                    subscriber.onComplete(); // 데이터 읽기가 종료되면 subscriber 의 onComplete 메서드를 호출한다.
                    executorService.shutdown();
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {
        subscriber.onComplete(); // 데이터 읽기가 취소되면 subscriber 의 onComplete 메서드를 호출한다.
    }
}
