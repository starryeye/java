package cold;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

@Slf4j
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

    /**
     * TODO, 의문점..
     * - 스레드가 한개인데 재귀적으로 호출되는데.. 어떻게 되는거지?
     * -> 해결) executorService.submit 메서드 호출 의미는.. executorService 에 작업을 제출하는 의미이다.
     * -> 그 자체로 작업을 수행하지 않는다. 내부적으로 작업 큐에 제출하고 끝이다. 이후 해당 스레드가 작업을 진행할 수 있으면 큐에 있던 작업을 진행한다.
     * -> 제출된 파라미터, 즉 Runnable 구현체 람다의 함수형 프로그래밍의 의미와도 일맥상통한다. (진행될 함수를 작업큐에 담는다.)
     * - subscriber.onNext(number) 와 iterator.remove() 가 순서가 바껴야 동작한다...
     */

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
                    subscriber.onNext(number); // subscriber 의 onNext 메서드를 호출하여 데이터를 push 해준다.
                    iterator.remove();
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
