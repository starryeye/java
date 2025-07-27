package concurrent.lock.sub2_condition.producer_consumer;

import util.bounded_buffer.BoundedBufferQueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyThreadLog.threadLog;

public class BoundedBufferQueueV2 implements BoundedBufferQueue {

    /**
     * 이전.
     * BoundedBufferProblemV1, BoundedBufferQueueV1
     *
     * 한정된 버퍼 문제를 해결하기 위해..
     * ReentrantLock, Condition::await(), signal() 를 사용하였다.
     *      여기서 사용되는 락과 "대기 스레드 큐" 공간, "락 획득 대기 큐" 공간은..
     *          Java 객체의 기본 모니터락과 "대기 스레드 큐" 공간, "락 획득 대기 큐" 공간이 아니다.
     *
     * 이전 V1 에서 Object::notify() 를 호출하면 대기 스레드 큐에 있는 스레드 중 하나를 깨우는데..
     * 다음과 같은 문제가 있다.
     *      1. 버퍼가 가득차있고 "대기 스레드 큐"에 생산자 스레드들이 많은 경우..
     *          소비자 스레드가 데이터를 소비하고 생산자 스레드들 중 하나가 깨워져서 생산을 정상적으로 했는데
     *          생산 후 notify() 에 의해 생산자 스레드가 깨워지는 문제가 존재한다.(데이터는 이미 가득 차있는데 생산자 스레드가 깨워져서 아무것도 못하고 다시 "대기 스레드 큐"에 진입)
     *      2. 버퍼가 비워져있고 "대기 스레드 큐"에 소비자 스레드들이 많은 경우..
     *          생산자 스레드가 데이터를 생산하고 소비자자 스레드들 중 하나가 깨워져서 데이터 소비를 정상적으로 했는데
     *          소비 후 notify() 에 의해 소비자 스레드가 깨워지는 문제가 존재한다.(데이터는 이미 없어졌는데 소비자 스레드가 깨워져서 아무것도 못하고 다시 "대기 스레드 큐"에 진입)
     * 1, 2 번 문제를 위해
     * "대기 스레드 큐"를 2개로 분리해서 소비자 스레드는 생산자 스레드만 깨우고 생산자 스레드는 소비자 스레드만 깨울 수 있도록 한다.
     *
     * ReentrantLock..
     *      임계 영역에 접근하는 스레드를 1개로 하기 위함
     *
     * Condition::await()
     *      임계영역내에서 대기할 때 락 반납을 같이하기 위함
     *      호출한 스레드는 RUNNABLE 상태에서 WAITING 상태가 됨
     *      호출한 스레드는 임계영역내의 "대기 스레드 큐"로 진입하고 락을 반납함
     * Condition::signal()
     *      임계영역내에서 대기하는 스레드("대기 스레드 큐"의 스레드)들 중 하나를 깨운다. (Queue 구조로 구현되어있어서 FIFO 로 깨운다.)
     *      깨워진 스레드는 WAITING 상태로 "락 획득 대기 큐"로 진입
     *      깨워진 스레드는 깨운 스레드가 임계영역을 벗어나서 락을 반납하면..
     *          락을 획득하고 WAITING 상태에서 RUNNABLE 상태가 된다.
     *
     */

    private final Queue<String> queue = new ArrayDeque<>();

    private final int max;

    private final Lock lock = new ReentrantLock();
    private final Condition producerCond = lock.newCondition(); // 생산자 "대기 스레드 큐"
    private final Condition consumerCond = lock.newCondition(); // 소비자 "대기 스레드 큐"

    public BoundedBufferQueueV2(int max) {
        this.max = max;
    }


    @Override
    public void put(String data) {
        lock.lock();
        try {

            // 임계 영역
            while (queue.size() == max) {
                threadLog("[put] queue is full, producer condition await() is call");
                try {
                    producerCond.await(); // 현재 스레드, RUNNABLE -> WAITING, 락 반납, "대기 스레드 큐"(생산자 전용) 진입
                    threadLog("[put] producer awake"); // 현재 스레드, WAITING -> RUNNABLE, 락 획득
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            consumerCond.signal(); // 소비자 전용 "대기 스레드 큐"의 대기 스레드 중 하나, WAITING 상태로 "락 획득 대기 큐"로 진입
            threadLog("[put] put data(" + data + ") in queue, consumer condition signal() is call");


        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {

            //임계 영역
            while (queue.isEmpty()) {
                threadLog("[take] queue is empty, consumer condition await() is call");
                try {
                    consumerCond.await(); // 현재 스레드, RUNNABLE -> WAITING, 락 반납, "대기 스레드 큐"(소비자 전용) 진입
                    threadLog("[take] consumer awake"); // 현재 스레드, WAITING -> RUNNABLE, 락 획득
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            producerCond.signal(); // 생산자 전용 "대기 스레드 큐"의 대기 스레드 중 하나, WAITING 상태로 "락 획득 대기 큐"로 진입
            threadLog("[take] get data(" + data + ") from queue, producer condition signal() is call");
            return data;

        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
