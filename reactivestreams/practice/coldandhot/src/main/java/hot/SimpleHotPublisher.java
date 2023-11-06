package hot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Future;

public class SimpleHotPublisher implements Flow.Publisher<Integer> {

    /**
     * [Hot Publisher]
     * - subscriber 가 없더라도 Publisher 가 데이터를 생성하고 stream 에 push 한다.
     * - Kafka 에서.. 가장 최신 offset 부터 받는 옵션을 떠올리면 쉽다.
     * - 여러 subscriber 에게 동일한 데이터를 실시간으로 전달하게 된다.
     * - subscribe 할 때마다 데이터가 다르게 받아질 수 있다.
     *
     *
     * [추가 개념 잡기]
     * - Hot Publisher 는 구독 여부와 관계없이 데이터를 발행한다.
     * 이는 라디오 방송이나 TV 채널과 유사하게, 모든 내용이 실시간으로 스트리밍되며, 구독자는 구독 시점부터의 데이터만 받을 수 있다.
     * - 여러 구독자가 동일한 데이터 스트림을 '공유'할 수 있다.
     * 이는 데이터가 개별적으로 아닌, 구독자들에게 동시에 방송됨을 의미한다.
     * - 실시간 주식 가격, 이벤트 또는 메시지 브로커에서의 실시간 메시지 스트림 등이 Hot Publisher 의 예가 될 수 있다.
     *
     * [Cold Publisher 와 비교..]
     * Cold Publisher 는 요청에 의해 시작되고 각 구독자에게 전용 데이터 스트림을 제공하는 반면,
     * Hot Publisher 는 계속적으로 데이터를 발행하고 여러 구독자가 같은 데이터를 공유할 수 있다.
     * 이러한 차이는 Reactive Streams 를 사용하여 시스템을 설계할 때 중요한 고려 사항이 된다.
     *
     * [참고..]
     * Reactor 의 Mono와 Flux
     * - Mono 는 0 또는 1개의 결과만을 발행할 수 있는 Reactive Streams Publisher 구현체이다.
     * 이는 단일 작업의 완료를 나타내거나 단일 값의 비동기 계산에 사용된다.
     * - Flux는 0개 이상의 결과를 발행할 수 있는 Reactive Streams Publisher 구현체다.
     * 이는 여러 개의 비동기 메시지를 처리하거나 여러 단계의 작업 흐름을 처리하는 데 사용된다.
     * - 두 경우 모두 기본적으로 Cold Publisher 로 동작합니다.
     * 즉, 이들은 구독자가 구독을 시작하기 전까지 데이터를 발행하지 않으며, 각 구독자에게 개별적인 데이터 스트림을 제공한다.
     *
     * Apache Kafka
     * - Kafka 는 분산 이벤트 스트리밍 플랫폼으로, 기본적으로 Hot Publisher 의 개념을 사용한다.
     * Kafka 토픽에 게시된 메시지는 실시간으로 계속 스트리밍되며, 구독자(이 경우 '소비자'라고 함)는 구독 시작 시점부터 메시지 스트림을 수신한다.
     * - 여러 소비자가 동일한 토픽의 메시지를 동시에 '구독'할 수 있으며, 메시지는 소비자 그룹 간에 균등하게 분배될 수 있다.
     * - Kafka 는 메시지를 실시간으로 처리하는 환경에 적합하며, 대규모 데이터 파이프라인과 실시간 분석을 처리하는데 널리 사용된다.
     *
     * [Hot Publisher 와 스레드]
     * hot publisher 는 subscriber 와 스레드가 분리된 모델인가..?
     * Hot Publisher와 Cold Publisher의 주요 차이점은 데이터의 발행 방식과 구독자와의 상호 작용에 있지,
     * 특정 스레드 모델이나 실행 방식에 바로 연결되지는 않는다.
     * 그러나 Hot Publisher에서는 종종 구독자와 분리된 스레드에서 데이터가 발행되곤 한다.
     *
     * - Hot Publisher
     * Hot Publisher 는 구독 여부에 관계없이 데이터를 발행하고, 여러 구독자가 동일한 데이터 스트림을 공유할 수 있다.
     * 이러한 방식은 이벤트가 독립적인 스레드나 외부 시스템에서 생성되는 경우에 흔히 볼 수 있다(예: 마우스 이벤트, 주식 가격 틱, 웹소켓을 통한 메시지 등).
     * 이 경우, 이벤트는 Publisher 가 동작하는 스레드와는 독립적으로 발생하며, 구독자는 각자의 스케줄링 정책 또는 스레드에서 이벤트를 처리한다.
     * Hot Publisher의 이러한 비동기적 특성 때문에, 구독자가 이벤트를 받을 준비를 하기 전에 이벤트가 발행될 수도 있는 것이다.
     *
     * - 스레드 분리에 대한 추가 고려 사항
     * Reactive 프로그래밍 모델에서, 스트림 작업은 종종 "non-blocking" 이다.
     * 즉, 오래 걸리는 작업이 특정 스레드를 차단하여 다른 작업이 그 스레드에서 수행되지 못하게 만들지 않아야 한다.
     * 실제로, 구독 로직과 발행 로직이 서로 다른 스레드에서 실행되도록 스케줄링할 수 있다.
     * 이는 시스템의 확장성을 높이고, 각 구성 요소가 서로에게 방해받지 않고 동시에 동작할 수 있게 해준다.
     * 특히 Hot Publisher에서는 이벤트 발생의 타이밍이 예측 불가능할 수 있기 때문에, 이벤트 처리를 위해 별도의 스레드를 사용하는 것이 일반적이다.
     * 결론적으로, Hot Publisher에서 발행자와 구독자의 스레드가 분리될 수 있지만,
     * 이는 Reactive Streams 구현의 설정 및 스케줄링 전략에 따라 다를 수 있다.
     * 이러한 분리는 시스템 리소스를 보다 효율적으로 활용하고 시스템의 전반적인 반응성을 향상시키기 위해 종종 사용된다.
     */

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
