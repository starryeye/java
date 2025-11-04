package parallel_stream;

public interface Warning {

    /**
     * Fork/Join 프레임워크를 사용하기 위한 방법은 보통..
     *      1. Stream API 에서 parallel() 메서드를 이용하면 Fork/Join common pool 사용됨
     *      2. CompletableFuture 에서 비동기 처리를 할때, 작업자(ExecutorService)를 지정하지 않으면 Fork/Join common pool 사용됨
     *      3. 직접 ForkJoinPool 을 생성하여 사용
     * 으로.. 3가지가 있다.
     *
     *
     * Fork / Join Framework 를 사용하기 앞서 주의 사항을 알아보자.
     *
     * 1. Fork / Join Framework 는 CPU bound 작업에만 사용해야한다.
     *      I/O bound 작업에 사용하면 아래와 같은 문제가 있다.
     *          - ForkJoinPool 은 보통 CPU core 수에 맞춰 제한된 갯수의 thread 를 사용한다.
     *              I/O 작업으로 스레드가 blocking (WAITING 상태) 되면, 해당 thread 를 처리하는 CPU core 가 유휴 상태가 되지만,
     *              thread 는 CPU 수에 맞춰져 있으므로 결국 다음 작업이 많더라도 CPU core 는 일을 하지 않게 된다.
     *              그렇다고해서, I/O 작업때문에 thread 를 늘려도 절대적인 I/O 대기시간은 줄어들지 않으며 context switching 비용만 증가한다.
     *          - work stealing 알고리즘은 유휴 thread 가 작업이 많은 스레드의 작업을 뺏어서
     *              쉬는 thread 및 CPU core 가 없도록하는게 핵심인데 thread 가 I/O 대기하는 경우에..
     *              thread 가 WAITING 상태가 되면서 다른 작업도 못하고 해당 thread 작업을 처리하는 CPU core 도 유휴 상태가 되어
     *              작업을 처리하지 못하는 공백시간이 생긴다.
     *              -> 결국 work stealing 알고리즘이 I/O bound 작업 처리에 맞지 않음을 볼 수 있음
     * 2. 동시에 여러 외부 스레드들이 Fork/Join Framework 에 여러 개의 작업 묶음을 요청하면,
     *      최초의 작업 묶음은 Fork/Execute/Join 을 수행하며 병렬적으로 잘 수행되지만
     *      두번째 작업 묶음부터는 이전의 작업 묶음이 어느정도 다 처리 되기까지 대기하므로.. 병목현상이 존재한다.
     *      -> 사실, 고정 thread pool 은 모두 이 문제를 가지고 있지만,
     *          작업을 알아서 나누고 병합한다고 해서 문제가 없다는 것은 아님을 강조하기 위해 씀.
     *
     *
     * 참고
     * I/O bound 작업을 처리할때는 Fork/Join Framework 를 사용하지 말고 ExecutorService 를 선택하자.
     * 병렬 처리 코드를 작성하는데 thread pool 을 지정하지 않았다면, Fork/Join common pool 을 사용했을 가능성이 있으므로 주의해야한다.
     *
     *
     */
}
