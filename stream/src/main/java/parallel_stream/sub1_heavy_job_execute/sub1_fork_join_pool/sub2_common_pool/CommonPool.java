package parallel_stream.sub1_heavy_job_execute.sub1_fork_join_pool.sub2_common_pool;

public class CommonPool {

    /**
     * Java 8 에서 Fork/Join Common pool 이 도입 되었다.
     *
     * Fork/Join common pool 에 대해 알아본다.
     *      application 내에서 단일 인스턴스로 공유된다.
     *          ForkJoinPool.commonPool(); 로 호출하면, 해당 인스턴스를 받아 사용할 수 있다.
     *      병렬 처리에 편리하게 사용됨.
     *          Stream API 에서 parallel() 를 사용하면 내부적으로 common pool 이 사용됨
     *          CompletableFuture 에서 별도의 thread pool 을 지정하지 않고 비동기 처리 요청하면 common pool 이 사용됨
     *      application 이 동작하는 컴퓨터의 cpu core 수 -1 만큼의 thread 갯수로 설정된다.
     *      common pool 의 thread 는 데몬 스레드이다.
     *      ForkJoinPool 을 직접 생성하여 사용할 때와 다르게 common pool 을 사용하면..
     *          작업을 요청한 스레드도 작업에 참여한다. -> 그래서 cpu core 수에서 1 을 뺀만큼 thread 갯수가 설정된 것이다.
     */

    public static void main(String[] args) {

    }
}
