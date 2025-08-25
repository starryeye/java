package concurrent.sub7_synchronous_asynchronous_blocking_nonblocking.functional;

import java.util.function.Consumer;

public class AsynchronousAndBlocking {

    /**
     * asynchronous, blocking 예시
     *
     * asynchronous
     *      Caller 는 Callee 의 결과에 관심이 없다.
     *      -> Callee 가 수행한 결과를 반환 받지도 않는다.
     *              Caller 는 Callee 가 수행한 결과에 +1 을 하고 로그를 남겨야 했지만, Callee 에게 양도함.
     * blocking
     *      Caller 가 Callee 를 호출한 후, Callee 가 작업하는 동안 Caller 는 아무것도 할 수 없음 (Caller 의 제어권은 Callee 에 존재)
     *      -> getResult() 메서드를 호출하면, Callee 의 코드가 모두 수행될 때 까지 Caller 의 코드는 수행되지 않는다.
     *
     */

    public static void main(String[] args) {

        // Caller 영역

        getResult(result -> {
            result++;
            System.out.println(result);
        });
    }

    private static void getResult(Consumer<Integer> callback) {

        // Callee 영역

        int result = 100;

        callback.accept(result);
    }
}
