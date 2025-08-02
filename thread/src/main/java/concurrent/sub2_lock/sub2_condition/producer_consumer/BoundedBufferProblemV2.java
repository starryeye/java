package concurrent.sub2_lock.sub2_condition.producer_consumer;

import util.bounded_buffer.BoundedBufferProblem;
import util.bounded_buffer.BoundedBufferQueue;

public class BoundedBufferProblemV2 extends BoundedBufferProblem {

    /**
     * 이전.
     * BoundedBufferProblemV1, BoundedBufferQueueV1
     *
     * BoundedBufferQueueV2 에 대해..
     * bounded buffer problem (producer consumer problem) 을 다뤄본다.
     */

    public static void main(String[] args) {
        BoundedBufferQueue queue = new BoundedBufferQueueV2(2); // 데이터 버퍼 2칸

        producerFirst(queue); // producer 3개가 0.1초 간격으로 실행된 후, consumer 3개가 0.1초 간격으로 실행된다.
//        consumerFirst(queue); // consumer 3개가 0.1초 간격으로 실행된 후, producer 3개가 0.1초 간격으로 실행된다.
    }
}
