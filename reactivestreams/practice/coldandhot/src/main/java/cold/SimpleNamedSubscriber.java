package cold;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

@Slf4j
public class SimpleNamedSubscriber<T> implements Flow.Subscriber<T> {

    /**
     * subscriber 는 다양한 이벤트가 들어오는 채널을 구현한다.
     *
     * subscribe 메서드
     * - publisher 의 subscribe 메서드를 호출하면 내부에서 subscriber 의 onSubscribe 메서들 호출하여 subscription 을 넣어준다.
     *
     * onNext 메서드
     * - publisher 로 부터 요청(데이터)을 받을 수 있는 채널이다. (호출 됨, push 모델)
     *
     * onError 메서드
     * - 에러 이벤트를 받는 채널
     *
     * onComplete 메서드
     * - 완료 이벤트를 받는 채널
     */

    private Flow.Subscription subscription;
    private final String name;

    public SimpleNamedSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

        this.subscription = subscription; // publisher 로 부터 제공 받은 리모콘

        this.subscription.request(1); // subscription 에 요청을 1 만큼 받는다고 설정

        log.info("[onSubscribe] subscriber={}, tx={}", name, Thread.currentThread().getName());
    }

    @Override
    public void onNext(T item) {

        log.info("[onNext] subscriber={}, item={}, tx={}", name, item, Thread.currentThread().getName());

        this.subscription.request(1); // subscription 에 요청을 1 만큼 받는다고 설정
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("[onError] subscriber={}, message={}, tx={}", name, throwable.getMessage(), Thread.currentThread().getName());
    }

    @Override
    public void onComplete() {
        log.info("[onComplete] subscriber={}, tx={}", name, Thread.currentThread().getName());
    }

    public void cancel() {
        log.info("[cancel] subscriber={}, tx={}", name, Thread.currentThread().getName());
        this.subscription.cancel();
    }
}
