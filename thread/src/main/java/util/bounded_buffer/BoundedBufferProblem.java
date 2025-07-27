package util.bounded_buffer;

import java.util.ArrayList;
import java.util.List;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class BoundedBufferProblem {

    // producer 3개가 0.1초 간격으로 실행된 후, consumer 3개가 0.1초 간격으로 실행된다.
    protected static void producerFirst(BoundedBufferQueue queue) {
        threadLog("== [producer tasks first] start, " + queue.getClass().getSimpleName() + " ==");
        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
        threadLog("== [producer tasks first] end, " + queue.getClass().getSimpleName() + " ==");
    }

    // consumer 3개가 0.1초 간격으로 실행된 후, producer 3개가 0.1초 간격으로 실행된다.
    protected static void consumerFirst(BoundedBufferQueue queue) {
        threadLog("== [consumer tasks first] start, " + queue.getClass().getSimpleName() + " ==");
        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        threadLog("== [consumer tasks first] end, " + queue.getClass().getSimpleName() + " ==");

    }

    private static void startProducer(BoundedBufferQueue queue, List<Thread> threads) {
        System.out.println();
        threadLog("producer start");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            threads.add(producer);
            producer.start();
            mySleep(100);
        }
    }

    private static void startConsumer(BoundedBufferQueue queue, List<Thread> threads) {
        System.out.println();
        threadLog("consumer start");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            mySleep(100);
        }
    }

    private static void printAllState(BoundedBufferQueue queue, List<Thread> threads) {
        System.out.println();
        threadLog("print current state, queue: " + queue);
        for (Thread thread : threads) {
            threadLog(thread.getName() + ": " + thread.getState());
        }
    }
}
