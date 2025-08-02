package thread.sub4_state.sub7_producer_consumer;

import util.bounded_buffer.BoundedBufferQueue;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyThreadLog.threadLog;

public class BoundedBufferQueueV1 implements BoundedBufferQueue {

    /**
     * 사전 지식.
     *      모든 객체에는 모니터 락과 "대기 스레드 큐", "락 획득 대기 큐"가 존재한다.
     *      모니터 락
     *          synchronized 키워드를 사용할 경우 임계영역에 진입하는 스레드가 획득해야하는 락이다.
     *          1개의 스레드만 진입 가능, 임계영역을 벗어나면 획득했던 락을 반납한다.
     *          락 획득을 대기하는 스레드는 BLOCKED 상태로 차단된다. (cpu 소모 X, "락 획득 대기 큐" 에 진입)
     *      "대기 스레드 큐"
     *          임계 영역 내에서 Object::wait() 를 호출한 스레드가 진입하여 대기하는 공간이다. (RUNNALBE -> WAITING, 락 반납)
     *          임계 영역 내에서 Object::notify() 를 호출하면 "대기 스레드 큐"에 존재하는 스레드 중 하나가 깨워진다. (WAITING -> BLOCKED, "락 획득 대기 큐"로 간다)
     *              notify() 를 호출한 스레드가 임계영역을 벗어나서 락을 반납하면, "대기 스레드 큐"에서 깨워진 스레드가 락을 획득한다. (BLOCKED -> RUNNABLE)
     *      "락 획득 대기 큐"
     *          synchronized 키워드를 사용할 경우 모니터락을 획득하지 못하여 임계영역 밖에서 대기하는 스레드의 대기 공간이다. (RUNNABLE -> BLOCKED)
     *          임계 영역 내의 스레드가 락을 반납하면 "락 획득 대기 큐"에 있던 스레드 중 하나가 락을 획득 하면서 임계 영역 내로 진입할 수 있다. (BLOCKED -> RUNNABLE)
     *
     * 한정된 버퍼 문제를 해결하기 위해..
     * synchronized, Object::wait(), notify() 를 사용하였다.
     *
     * 해결된 데드락 문제1 (버퍼에 데이터가 없음)
     *      소비자 스레드가 임계 영역에 들어와서(락획득) 버퍼를 확인 해보니 버퍼에 데이터가 없어서 대기해야함
     *          소비자 스레드 락획득후 대기
     *      생산자 스레드가 임계 영역에 들어와서 버퍼에 데이터를 적재해야하는데 소비자 스레드가 락을 획득하고 있어서 대기하는 문제 (데드락)
     *          생산자 스레드 락획득을 위해 대기
     * 해결된 데드락 문제2 (버퍼에 데이터가 가득참)
     *      생산자 스레드가 임계 영역에 들어와서(락획득) 버퍼를 확인 해보니 버퍼에 데이터가 가득차서 대기해야함
     *          생산자 스레드 락획득후 대기
     *      소비자 스레드가 임계 영역에 들어와서 버퍼에 데이터를 꺼내야하는데 생산자 스레드가 락을 획득하고 있어서 대기하는 문제 (데드락)
     *          소비자 스레드 락획득을 위해 대기
     *
     * synchronized..
     *      임계 영역에 접근하는 스레드를 1개로 하기 위함
     *
     * Object::wait()
     *      임계영역내에서 대기할 때 락 반납을 같이하기 위함 (해결된 데드락 문제1, 2 를 위함)
     *      호출한 스레드는 RUNNABLE 상태에서 WAITING 상태가 됨
     *      호출한 스레드는 임계영역내의 "대기 스레드 큐"로 진입하고 락을 반납함
     * Object::notify()
     *      임계영역내에서 대기하는 스레드("대기 스레드 큐"의 스레드)들 중 하나를 깨운다. (JVM 구현에 따라 스레드가 깨워지는 순서가 다름, 랜덤이라 생각해야함)
     *      깨워진 스레드는 WAITING 상태에서 BLOCKED 상태가 됨 ("락 획득 대기 큐"로 진입)
     *      깨워진 스레드는 깨운 스레드가 임계영역을 벗어나서 락을 반납하면..
     *          락을 획득하고 BLOCKED 상태에서 RUNNABLE 상태가 된다.
     *
     * 참고.
     * Object::notifyAll()
     *      임계영역내에서 대기하는 스레드("대기 스레드 큐"의 스레드)들 모두를 깨운다.
     *      깨워진 모든 스레드는 WAITING 상태에서 BLOCKED 상태가 됨 ("락 획득 대기 큐"로 진입)
     *      깨워진 스레드는 깨운 스레드가 임계영역을 벗어나서 락을 반납하면..
     *          락을 획득하고 BLOCKED 상태에서 RUNNABLE 상태가 된다.
     *          이후 해당 스레드가 락을 반납하면 다음 스레드가 동일하게 락을 획득할 수 있다.
     *
     * 다음
     * BoundedBufferProblemV2, BoundedBufferQueueV2
     */

    private final Queue<String> queue = new ArrayDeque<>();

    private final int max;

    public BoundedBufferQueueV1(int max) {
        this.max = max;
    }


    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            threadLog("[put] queue is full, wait() is call");
            try {
                wait(); // 현재 스레드, RUNNABLE -> WAITING, 락 반납, "대기 스레드 큐" 진입
                threadLog("[put] producer awake"); // 현재 스레드, BLOCKED -> RUNNABLE, 락 획득
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        notify(); // "대기 스레드 큐"의 대기 스레드 중 하나, WAITING -> BLOCKED, "락 획득 대기 큐"로 진입
        threadLog("[put] put data(" + data + ") in queue, notify() is call");
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            threadLog("[take] queue is empty, wait() is call");
            try {
                wait(); // 현재 스레드, RUNNABLE -> WAITING, 락 반납, 대기 스레드 큐 진입
                threadLog("[take] consumer awake"); // 현재 스레드, BLOCKED -> RUNNABLE, 락 획득
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        notify(); // "대기 스레드 큐"의 대기 스레드 중 하나, WAITING -> BLOCKED, "락 획득 대기 큐"로 진입
        threadLog("[take] get data(" + data + ") from queue, notify() is call");
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
