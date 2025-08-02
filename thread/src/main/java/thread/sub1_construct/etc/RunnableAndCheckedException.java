package thread.sub1_construct.etc;

public class RunnableAndCheckedException {

    /**
     * interface 에서 throws 를 정의하지 않은 추상 메서드를 구현체에서 오버라이딩할 때, throws 를 붙이지 못함.
     *     예시로 Runnable::run 구현에서 throws 절을 사용하지 못함.
     *     언체크 예외는 throws 절이 필요 없지만, 체크 예외는 throws 절이 필요해서 붙이고 싶을때를 말함.
     *         Runnable::run 구현에서 예외를 다룰때는 언체크 예외로 다루거나 체크예외일 경우에는 try-catch 로 예외를 반드시 처리해줘야함.
     * 만약, interface 에서 throws 를 정의해놓았다면, 그 하위 타입을 throws 하도록 오버라이딩 가능
     *
     * 참고. Callable
     */

    private static class MyRunnable implements Runnable {

        // interface 에서 throws 절이 없으므로 구현체에서 사용할 수 없다.
        @Override
        public void run() /* throws InterruptedException */ {
            // Thread.sleep(1000);
        }
    }
}
