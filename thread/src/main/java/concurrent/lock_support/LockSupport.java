package concurrent.lock_support;

public class LockSupport {

    /**
     * synchronized 는 아래와 같은 단점이 존재한다.
     *      BLOCKED 상태를 이용하기 때문에..
     *          락이 풀릴 때 까지 무한 대기하여, 특정 시간까지만 대기할 수 없다.(타임 아웃 기능 X)
     *          BLOCKED 는 인터럽트가 안먹힌다.
     *      여러 스레드가 BLOCKED 상태라면 어떤 스레드가 락을 획득할지 보장이 안되어 무한 대기할 가능성이 존재한다.
     *
     * LockSupport 는 java.util.concurrent (Java 1.5 버전) 라는 동시성 문제 해결을 위한 라이브러리에서 제공한다.
     * 위의 synchronized 의 단점을 해결해준다.
     *
     * 락 획득에 실패한 스레드는
     * 스레드를 BLOCKED 상태로 차단 시키지 않고 WAITING 상태로 대기 시킨다.
     *
     * park()
     *      스레드를 WAITING 상태로 변경한다.
     * parkNano(nanos)
     *      스레드를 TIMED_WAITING 상태로 변경하고 정한 시간이 지나면 RUNNABLE 상태로 변경된다.
     * unpark(thread)
     *      파라미터로 전달한 WAITING 상태의 스레드를 RUNNABLE 상태로 변경한다.
     *
     * 예시
     * park - unpark
     * park - interrupt
     * parkNanos
     *
     *
     */

    public static void main(String[] args) {

    }
}
