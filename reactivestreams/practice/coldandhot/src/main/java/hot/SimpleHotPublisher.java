package hot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Future;

public class SimpleHotPublisher implements Flow.Publisher<Integer> {

    private final ExecutorService publisherExecutor = Executors.newSingleThreadExecutor(); // 주기적 값 생성을 위한 스레드
    private final Future<Void> task;
    private List<Integer> numbers = new ArrayList<>();
    private List<SimpleHotSubscription> subscriptions = new ArrayList<>(); // subscription 들을 저장하고 있는다.

    public SimpleHotPublisher() {

        numbers.add(1);

        task = publisherExecutor.submit(() -> { // 주기적으로 값을 생성한다.
            for(int i = 2;!Thread.interrupted();i++) {
                numbers.add(i);
                subscriptions.forEach(SimpleHotSubscription::wakeup); // 저장된 subscription 에게 데이터 생성을 알린다. (데이터를 보낸다. onNext 호출)
                Thread.sleep(100); // 0.1 초 간격
            }

            return null;
        });
    }

    public void shutdown() {
        this.task.cancel(true); // future 의 cancel 메서드 호출을 하여 스레드 interrupt 시킴
        publisherExecutor.shutdown(); // 더이상의 작업을 받지 않는다. graceful shutdown..
        //참고, 작업이 무한 루프라.. interrupt 시키지 않으면 shutdown 되지 않음..
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        // subscriber 등록한다.

        var subscription = new SimpleHotSubscription(subscriber); // subscription 생성

        subscriber.onSubscribe(subscription); // subscriber 에게 subscription 전달

        subscriptions.add(subscription); // subscription 저장
    }

    /**
     * subscription
     */
    private class SimpleHotSubscription implements Flow.Subscription {
        private int offset; // susbscriber 의 현재 offset
        private int requiredOffset; // susbscriber 의 목표 offset
        private final Flow.Subscriber<? super Integer> subscriber;
        private final ExecutorService subscriptionExecutorService = Executors.newSingleThreadExecutor(); // 별도의 스레드, 데이터 push 용

        public SimpleHotSubscription(Flow.Subscriber<? super Integer> subscriber) {
            int lastElementIndex = numbers.size() - 1;
            this.offset = lastElementIndex;
            this.requiredOffset = lastElementIndex;
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            requiredOffset += n;

            onNextWhilePossible();
        }

        public void wakeup() {
            onNextWhilePossible();
        }

        @Override
        public void cancel() {
            this.subscriber.onComplete();
            if (subscriptions.contains(this)) { // publisher 에 저장되어있는 subscription 을 삭제
                subscriptions.remove(this);
            }
            subscriptionExecutorService.shutdown();
        }

        private void onNextWhilePossible() {
            subscriptionExecutorService.submit(() -> {
                while (offset < requiredOffset && offset < numbers.size()) {
                    var item = numbers.get(offset);
                    subscriber.onNext(item);
                    offset++;
                }
            });
        }
    }
}
