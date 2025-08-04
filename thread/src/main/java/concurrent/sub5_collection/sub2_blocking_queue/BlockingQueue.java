package concurrent.sub5_collection.sub2_blocking_queue;

public class BlockingQueue {

    /**
     * BlockingQueue (차단 큐) 는 BoundedBufferProblem 시리즈에서 본 것 처럼
     * 생산자/소비자 스레드가 공유자원(큐)를 두고 생산/소비 필요에 의해 차단(개념상 차단, 실제 스레드는 WAITING)되는 특별한 자료구조이다.
     *      이에 따라, 차단 (blocking) 큐로 불린다.
     * 반대로, 일반적인 Queue 는 스레드가 차단되는 개념 없이 구현되므로 비차단 (non-blocking) 큐로 불린다.
     *
     * 차단 큐에 대해 java.util.concurrent 에서 제공되는 객체를 정리해본다.
     *
     * ArrayBlockingQueue
     *      큐 크기가 고정된 blocking queue
     *      락 획득 fair mode 를 지원한다. (성능 저하가 있음)
     * LinkedBlockingQueue
     *      큐 크기가 무한하거나 고정된 blocking queue
     * PriorityBlockingQueue
     *      element 에 우선순위를 지정하여 우선순위 높은 element 을 먼저 처리하도록 함
     * SynchronousQueue
     *      큐의 크키가 0 이다.
     *      큐 없이 생산자, 소비자가 직접 만나 데이터를 주고 받는다.
     * DelayQueue
     *      element 가 생산되고, 특정 시간이 지난 후 소비 되는 기능을 지원한다.
     */
}
