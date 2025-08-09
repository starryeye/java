package concurrent.sub6_executor.sub9_thread_pool_strategy;

public class ExecutorsThreadPoolStrategy {

    /**
     *
     * Executors 가 제공하는 thread pool 전략
     *
     * Executors.newSingleThreadPool()
     *      thread pool 에 thread 1개만 사용
     *      blocking queue 에 size 제한이 없다. (LinkedBlockingQueue)
     *      특징
     *          테스트용도로 사용
     *
     * Executors.newFixedThreadPool(nThreads)
     *      thread pool 에 파라미터로 전달한 갯수만큼 corePoolSize, maximumPoolSize 를 정한다. (thread 갯수 고정)
     *      blocking queue 에 size 제한이 없다. (LinkedBlockingQueue)
     *      특징
     *          CPU, 메모리 자원 사용량의 한계를 정해놓고 사용하는 느낌
     *          작업 큐 사이즈의 제한이 없어서 작업을 담고 있는 것 자체는 문제가 없다. (아무리 많아도 넣어두기만함)
     *          작업 처리(소비) 속도 보다 작업 요청(생산) 속도가 빠르면, 작업 큐에만 계속 작업이 담기기만 할 것이다.
     *
     * Executors.newCachedThreadPool()
     *      thread pool 에 corePoolSize 는 0 이고 maximumPoolSize 는 Integer.MAX_VALUE 이고, keepAliveTime 은 60 초이다.
     *      blocking queue 에 작업을 저장하지 않는다. (SynchronousQueue)
     *      특징
     *          blocking queue 가 없다고 보면 되므로, 작업 요청 스레드가 작업 실행 스레드에게 작업을 직접 전달해준다.
     *          모든 작업은 실행되기까지 대기 없이 바로 실행된다.
     *          요청된 작업 수 만큼 thread 가 증가되고 감소된다. (Integer.MAX_VALUE 개 까지 생성될 수 있음)
     *          하드웨어 CPU, 메모리 자원의 사용량 한계를 넘어서까지 사용되어 시스템이 다운될 수 있다. (Integer.MAX_VALUE 개 까지 생성될 수 있음)
     *
     *
     *
     *
     */
}
