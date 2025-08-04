package concurrent.sub6_executor.sub1_explain;

public interface Executor1 {

    /**
     * 도입 배경
     *
     * 개발자가 Thread 객체를 직접 생성해서 사용하는 경우의 대표 문제점
     *      1. 스레드가 필요할 때마다 생성하면 스레드 생성 시간으로 인한 성능 문제가 있다.
     *              스레드를 생성하는 작업은 단순 객체를 생성하는게 아니라.. 아래의 이유로 무거운 작업이다.
     *                  - 스레드의 call stack 을 위한 stack 메모리 할당이 필요
     *                  - OS kernel 수준의 시스템 콜을 통해 처리
     *                  - 새로운 스레드가 생성되면 OS 스케줄러의 오버헤드 발생
     *                  - 보통 1MB 메모리 사용
     *      2. 하드웨어 CPU, 메모리 자원 한계로 인해 무한히 스레드를 만들 수 없음
     *      3. Runnable 인터페이스는 반환 값, 예외 발생 개념이 없음
     *
     * 위 3가지 문제의 해결을 해야한다.
     *
     * 해결
     * thread pool 도입
     *      - thread pool 에 "미리 thread 를 생성"해두고 작업이 끝난 thread 는 thread pool 에서 WAITING 상태로 대기한다. (thread 재사용)
     *          -> 1번 해결
     *      - thread pool 에서 사용하는 thread 는 정해둔 갯수만큼만 생성해둔다.
     *          -> 2번 해결
     *
     * thread pool 의 생산자 소비자
     *      어떤 스레드들은 thread pool 에 처리할 작업을 생산해주고
     *      thread pool 의 스레드들은 처리할 작업을 소비한다.
     *      따라서, thread pool 에는 blocking queue (차단 큐) 개념이 사용된다.
     */

    void execute(Runnable command); // 작업(Runnable) 을 파라미터로 넘기면 Executor 내부 스레드들이 수행해준다.
}
