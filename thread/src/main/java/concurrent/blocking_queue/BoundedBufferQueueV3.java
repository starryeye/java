package concurrent.blocking_queue;

import util.bounded_buffer.BoundedBufferQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyThreadLog.threadLog;

public class BoundedBufferQueueV3 implements BoundedBufferQueue {

    /**
     * Java 에서 제공하는 BlockingQueue 를 이용하여 한정된 버퍼 문제를 해결할 수 있다.
     * 기본 개념은 V2 와 동일함 (내부 코드 보면 await(), signal(), 2 개의 "대기 스레드 큐" 보임)
     * 아래 예제 코드는 기존 BoundedBufferQueue 인터페이스로 호환성 맞추기 위해 BlockingQueue 를 Wrapping 하여 사용
     *
     * BlockingQueue 대표 제공 메서드
     *      put / take
     *          데이터 넣을 공간이 생기거나 / 가져갈 데이터가 생기거나 할 때까지..
     *              WAITING 대기 (interrupt 가능)
     *      offer / poll
     *          데이터 넣을 공간이 없거나 / 가져갈 데이터가 없으면..
     *              대기 없이 즉시 false 반환
     *      offer(timeout) / poll(timeout)
     *          데이터 넣을 공간이 생기거나 / 가져갈 데이터가 생기거나 할 때까지..
     *              특정 시간을 WAITING 대기 (interrupt 가능)
     *              특정 시간이 지나면 즉시 false 반환
     *      add / remove
     *          데이터 넣을 공간이 없거나 / 가져갈 데이터가 없으면..
     *              대기 없이 즉시 예외 발생 (IllegalStateException / NoSuchElementException)
     */

    BlockingQueue<String> queue;

    public BoundedBufferQueueV3(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {

        try {
            queue.put(data);
            threadLog("[put] put data(" + data + ") in queue");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String take() {
        try {
            String data = queue.take();
            threadLog("[take] get data(" + data + ") from queue");
            return data;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
