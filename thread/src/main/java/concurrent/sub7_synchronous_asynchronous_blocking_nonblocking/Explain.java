package concurrent.sub7_synchronous_asynchronous_blocking_nonblocking;

public interface Explain {

    /**
     * synchronous, asynchronous / blocking, non-blocking 에 대해 알아본다.
     *
     *
     * 함수 호출 관점
     *      함수를 호출하는 Caller 와 호출을 받는 Callee 로 본다.
     * synchronous / asynchronous
     *      synchronous
     *          Caller 가 Callee 의 결과에 관심이 있다.
     *              스레드 개념이 필요없음
     *      asynchronous
     *          Caller 가 Callee 의 결과에 관심이 없다.
     *              스레드 개념이 필요없음
     * blocking / non-blocking
     *      blocking
     *          Caller 가 Callee 를 호출한 후, Callee 가 작업하는 동안 Caller 는 아무것도 할 수 없음 (Caller 의 제어권은 Callee 에 존재)
     *              스레드 개념이 필요없음
     *      non-blocking
     *          Caller 가 Callee 를 호출한 후, Callee 가 작업하더라도 Caller 는 자유롭게 다른 작업가능 (Caller 의 제어권은 Caller 에 존재)
     *              스레드 개념이 필요하다
     *
     *
     * 참고.
     * 여기서 결과에 관심이 있다 없다에 대한 개념은..
     * 만약 Caller 가 Callee 의 반환 값을 가지고 Caller 가 무언갈 수행한다면
     *      결과에 관심이 있는 것이고..
     * 만약 Caller 가 Callee 의 반환 값을 받지 않거나(void)
     * 반환 값으로 어떤 작업을 해야하더라도 수행해야할 작업을 콜백개념으로 Callee 에 넘긴다면
     *      결과에 관심이 없는 것이다..
     *
     *
     *
     * I/O 관점 (스레드 하나짜리 application 과 kernel 사이로 생각하자)
     *      kernel 을 호출하는 것은 input/output stream 을 이용해서 호출한다고 생각하자
     * synchronous / asynchronous
     *      synchronous
     *          application 이 kernel 의 결과에 관심이 있다.
     *      asynchronous
     *          application 이 kernel 의 결과에 관심이 없다.
     * blocking / non-blocking
     *      blocking
     *          application 이 kernel 를 호출한 후, kernel 이 작업하는 동안 application 는 아무것도 할 수 없음 (application 의 제어권은 kernel 에 존재)
     *      non-blocking
     *          application 가 kernel 를 호출한 후, kernel 가 작업하더라도 application 는 자유롭게 다른 작업가능 (application 의 제어권은 application 에 존재)
     */
}
