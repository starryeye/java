package concurrent.sub8_completable_future.sub3_todo;

public interface Todo {

    /**
     * (1) 기본 실행
     * runAsync(Runnable)
     * supplyAsync(Supplier<T>)
     * (ExecutorService 지정 가능)
     *
     * (2) 단일 Future 후처리
     * thenApply / thenApplyAsync
     * thenAccept / thenAcceptAsync
     * thenRun / thenRunAsync
     *
     * (3) Future → Future 연결
     * thenCompose / thenComposeAsync (flatMap 패턴)
     *
     * (4) 여러 Future 병합
     * thenCombine / thenCombineAsync
     * thenAcceptBoth / thenAcceptBothAsync
     * runAfterBoth / runAfterBothAsync
     *
     * (5) 경쟁 조건 (Either)
     * applyToEither / applyToEitherAsync
     * acceptEither / acceptEitherAsync
     * runAfterEither / runAfterEitherAsync
     *
     * (6) 여러 Future 동시 관리
     * allOf() → 모든 작업 완료 대기
     * anyOf() → 하나만 끝나도 반환
     *
     * (7) 예외 처리
     * exceptionally
     * handle / handleAsync
     * whenComplete / whenCompleteAsync
     *
     * (8) 타임아웃/완료 제어 (Java 9+)
     * orTimeout, completeOnTimeout
     *
     * (9) 실전 활용
     * CPU-bound vs IO-bound 스레드 풀 분리
     * 외부 API 병렬 호출 → allOf
     * Fallback 처리 → exceptionally, handle
     */
}
