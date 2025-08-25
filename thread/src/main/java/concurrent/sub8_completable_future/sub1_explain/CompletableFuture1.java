package concurrent.sub8_completable_future.sub1_explain;

public interface CompletableFuture1 {

    /**
     * CompletableFuture 에 대해 알아본다.
     *
     * 용어 정리.
     * 호출 스레드 / 작업 스레드 로 부르도록 하겠다..
     *
     * CompletableFuture..
     *      Java 8 에 도입
     *      비동기 프로그래밍 지원
     *      Future, CompletionStage 를 구현한다.
     *          Future
     *              ExecutorService::submit(Callable) 의 반환타입으로 주요하게 사용된다.
     *              작업 스레드의 반환 값을 받을 수 있음 (Future::get())
     *          CompletionStage
     *              완료 시점(Completion)을 기반으로 다음 단계 Stage 를 연결할 수 있도록하는 개념..
     *              여러 개의 CompletionStage 간 체이닝을 통해 콜백을 지원하는 인터페이스
     *      즉, 작업 스레드의 결과를 컨트롤 하고 여러 개의 콜백 메서드를 메서드 체이닝 기능으로 편리하게 지원한다.
     *
     *
     * 참고.
     * Future 만을 사용하여도 콜백메서드를 작업 스레드로 넘겨서 수행시킬 수 있었지만,
     * 그러한 콜백메서드가 여러 개이거나 다양한 조건의 조합이 필요한 경우 구현이 너무 복잡해진다.
     * 또한, 콜백을 호출하는 코드가 작업 스레드 영역에 존재해야하고
     * 작업 스레드 영역의 코드는 SRP 원칙에 위반되는 모양새가 된다.
     *
     *
     */
}
