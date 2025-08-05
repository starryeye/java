package concurrent.sub6_executor.sub3_callable_and_future;

public class Future1 {

    /**
     * Future 가 제공하는 대표적인 기능에 대해 알아본다.
     *
     *
     * 메서드
     * boolean cancel(boolean mayInterruptIfRunning)
     *      아직 완료되지 않은 작업을 취소한다.
     *          작업이 아직 실행되지 않았다면, 실행하지 않고 취소
     *          작업이 이미 실행되는 중이라면, 파라미터에 따라 동작 달라짐
     *      파라미터
     *          true : Future 를 CANCELLED 상태로 변경, 작업이 실행 중이면 인터럽트를 호출해서 작업을 중단시킨다.
     *          false : Future 를 CANCELLED 상태로 변경, 작업이 실행 중이면 그대로 놔둔다.
     *      반환값은 CANCELLED 상태 변경 여부이다.
     *      취소된 Future 에 get() 메서드를 호출하면, CancellationException 발생한다.
     *
     * boolean isCancelled()
     *      작업 CANCELLED 상태 여부
     *
     * boolean isDone()
     *      작업 완료 여부
     *
     * State state()
     *      Java 19 이후 제공됨
     *      RUNNING, SUCCESS, FAILED, CANCELLED
     *
     * V get() throws InterruptedException, ExecutionException
     *      작업이 완료될 때 까지 대기하고, 완료되면 결과 반환된다.
     *      반환값은 작업의 결과이다.
     *      예외
     *          InterruptedException : get() 메서드 호출 스레드가 대기 중에 인터럽트 발생한 경우
     *          ExecutionException : 작업 실행 스레드에서 작업 중 예외가 발생한 경우, get() 메서드 호출 스레드에서 발생하는 예외이다.
     *
     * V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
     *      작업이 완료될 때 까지 파라미터로 전달한 일정 시간만큼 대기하고, 완료되면 결과 반환된다.
     *      반환값은 작업의 결과이다.
     *      예외
     *          InterruptedException : get() 메서드 호출 스레드가 대기 중에 인터럽트 발생한 경우
     *          ExecutionException : 작업 실행 스레드에서 작업 중 예외가 발생한 경우, get() 메서드 호출 스레드에서 발생하는 예외이다.
     *          TimeoutException : 파라미터로 전달한 일정 시간내에 작업이 완료되지 못하면, get() 메서드 호출 스레드에서 발생하는 예외이다.
     */
}
