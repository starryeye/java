package concurrent.sub7_synchronous_asynchronous_blocking_nonblocking.functional;

public class SynchronousAndBlocking {

    /**
     * synchronous, blocking 예시
     *
     * synchronous
     *      Caller 는 Callee 의 결과에 관심이 있다.
     *      -> 결과에 +1 을 하고 로그를 남김
     * blocking
     *      Caller 가 Callee 를 호출한 후, Callee 가 작업하는 동안 Caller 는 아무것도 할 수 없음 (Caller 의 제어권은 Callee 에 존재)
     *      -> getResult() 메서드를 호출하면, Callee 의 코드가 모두 수행될 때 까지 Caller 의 코드는 수행되지 않는다.
     *
     */

    public static void main(String[] args) {

        // Caller 영역

        int result = getResult();

        result++;
        System.out.println(result);
    }

    private static int getResult() {

        // Callee 영역

        int result = 100;

        return result;
    }
}
