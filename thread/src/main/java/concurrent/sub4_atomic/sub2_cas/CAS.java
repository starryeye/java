package concurrent.sub4_atomic.sub2_cas;

public class CAS {

    /**
     * CAS, 락프리, AtomicXXX, 낙관적 락 에 대한 개념에 대해
     * AtomisticInteger, IncrementConcurrentTest, IncrementPerformanceTest 에서 기술해놨지만..
     * 여기서는 락 방식과 CAS 방식에 대해 비교 정리 해본다.
     *
     * 락 방식
     *      비관적(pessimistic) 방식
     *          "대부분 충돌 할 것이다..." 라고 가정하고 코드를 작성함..
     *      데이터에 접근하기 전에 항상 락을 획득
     *      다른 스레드의 접근을 막음
     *
     * CAS 방식
     *      낙관적(optimistic) 방식
     *          "대부분 충돌하지 않을 것이다.." 라고 가정하고 코드를 작성함..
     *      락 획득 없이 데이터에 접근
     *      충돌이 발생하면 재시도로 해결
     *
     * 실무 포인트..
     * 충돌(CUD)이 잦다면, 비관적 방식이 유리하다.
     *      CUD 가 잦은데 낙관적 방식으로 하면 대부분의 경우 충돌이 나서 비관적 방식보다 결과적으로 더 않좋은 성능이 나올 것이다.
     * 충돌(CUD)이 잦지 않으면, 낙관적 방식이 유리하다.
     *      락 획득/반납 과정이 없으므로 연산 자체로만 보면 비관적 방식보다 빠르다. 충돌이 별로 없는 경우 낙관적 방식의 성능이 훨씬 좋을 것이다.
     *
     * 참고.
     * 간단한 CPU 연산은 너무 빨리 처리가 되기 때문에 충돌이 자주 발생하지 않는다.
     * IncrementConcurrentTest 의 BasicInteger 의 결과를 보면, 1000 개의 스레드 중 충돌이 일어난 경우는 거의 50 개 정도이다..
     *      이것도 사실 sleep 을 주어 동시성 문제가 더 잘 들어나도록 셋팅 한것이다. (해제하면 충돌은 1개도 나올까 말까이다.)
     * 결론적으로..
     *      락 방식으로하면, IncrementConcurrentTest 의 SynchronizedInteger 에서
     *          1000 개의 스레드가 모두 락 획득/반납 과정을 거치고.. 순차적으로 하나의 스레드만 실제 작업을 수행하게된다.
     *      CAS 방식으로하면, IncrementConcurrentTest 의 AtomisticInteger 에서
     *          1000 개의 스레드가 모두 동시에 수행되며.. 50 개의 스레드만 재시도 될 것이다..
     *
     *
     * 참고.
     * reference type 의 참조값 변경을 CAS 로 수행시키려면.. AtomicReference<T> 를 이용하면 된다.
     *
     * 참고
     * 락 방식을 사용하고 있는데 CAS 방식으로 전환 가능한지 알아보는.. 기준..
     *      1. 단일 공유 자원에 대한 것인지.. (단일 변수, reference type 인 경우 참조 값 변경)
     *      2. 조회 후 바로 업데이트인지..
     *          조회와 업데이트 사이에 sleep, I/O 작업 등이 없어야함
     *      3. 조건 분기나 루프가 있으면 가능성이 낮음..
     *
     */
}
