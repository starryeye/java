package thread.state.sub7_producer_consumer;

import util.bounded_buffer.BoundedBufferQueue;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyThreadLog.threadLog;

public class BoundedBufferQueueV1 implements BoundedBufferQueue {

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
                wait(); // 현재 스레드, RUNNABLE -> WAITING, 락 반납, 대기 스레드 큐 진입
                threadLog("[put] producer awake"); // 현재 스레드, BLOCKED -> RUNNABLE, 락 획득
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        notify(); // 대기 스레드 중 하나, WAIT -> BLOCKED
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
        notify(); // 대기 스레드 중 하나, WAIT -> BLOCKED
        threadLog("[take] get data(" + data + ") from queue, notify() is call");
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
