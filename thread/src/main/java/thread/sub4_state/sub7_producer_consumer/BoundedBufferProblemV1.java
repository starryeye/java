package thread.sub4_state.sub7_producer_consumer;

import util.bounded_buffer.BoundedBufferProblem;
import util.bounded_buffer.BoundedBufferQueue;

public class BoundedBufferProblemV1 extends BoundedBufferProblem {

    /**
     * BoundedBufferQueueV1 에 대해..
     * bounded buffer problem (producer consumer problem) 을 다뤄본다.
     *
     * bounded buffer problem..
     *      한정된 버퍼(공유 자원)에 여러개의 소비자 스레드와 여러개의 생산자 스레드가 접근할 때의 문제이다.
     *      문제
     *          1. 버퍼가 가득찬 경우 생산자 스레드는 어떤행동을 할지..
     *              - 데이터를 담지 못하고 즉시 리턴(데이터 누락, 비정상이라 본다.)
     *              - 한정된 버퍼에 자리가 생길때 까지 대기
     *          2. 버퍼가 완전히 비어있을 경우 소비자 스레드는 어떤행동을 할지..
     *              - 데이터를 꺼내지 못하고 즉시 리턴(데이터 null 을 get, 비정상이라 본다.)
     *              - 버퍼에 꺼낼수 있는 데이터가 생길때 까지 대기
     *
     * 생산자 스레드
     *      한정된 버퍼에 데이터를 담는 역할의 스레드이다.
     * 소비자 스레드
     *      한정된 버퍼에서 데이터를 꺼내는 역할의 스레드이다.
     *
     *
     * 문제에 대해 어떻게 해결하는지는 BoundedBufferQueue 구현체를 보자.
     */

    public static void main(String[] args) {
        BoundedBufferQueue queue = new BoundedBufferQueueV1(2); // 데이터 버퍼 2칸

        producerFirst(queue); // producer 3개가 0.1초 간격으로 실행된 후, consumer 3개가 0.1초 간격으로 실행된다.
//        consumerFirst(queue); // consumer 3개가 0.1초 간격으로 실행된 후, producer 3개가 0.1초 간격으로 실행된다.
    }
}
