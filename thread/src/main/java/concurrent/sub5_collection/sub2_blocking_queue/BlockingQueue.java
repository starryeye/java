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
     * LinkedBlockingQueue
     * PriorityBlockingQueue
     * SynchronousQueue
     * DelayQueue
     */
}
