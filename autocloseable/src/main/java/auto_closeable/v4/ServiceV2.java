package auto_closeable.v4;

import auto_closeable.common.CallException;
import auto_closeable.common.CloseException;

public class ServiceV2 {

    public static void main(String[] args) {

        try {

            logic(); // 비즈니스 로직

        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("suppressedEx = " + throwable);
            }
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {

        // ResourceV2 객체는 AutoCloseable 을 구현하기 때문에, try with resources 사용 가능하다.
        try (
                ResourceV2 resourceA = new ResourceV2("resourceA");
                ResourceV2 resourceB = new ResourceV2("resourceB")) {

            resourceA.call();
            resourceB.callEx(); // resourceB 자원을 사용하던 도중 예외가 발생하였다.
        } catch (CallException e) {
            System.out.println("ex = " + e); // 원래는 필요없음, AutoCloseable 에 의한 자원 정리가 빠른지 catch 구문이 빠른지 확인용
            throw e;
        }

        // [문제1 해결] try with resource 로 비즈니스 로직 수행도중 예외 발생하더라도 자동으로 자원 정리를 해준다.
        // [문제2 해결] finally 영역 자체가 없어졌으므로 resourceA, resourceB 참조 변수는 try 영역 내부에서만 사용하면 되기 때문에 외부로 변수를 뺄 필요가 없다.
        // [문제3 해결] AutoCloseable 에 의한 자원정리가 catch 구문보다 빠르다.
        // [문제4 해결] AutoCloseable 을 사용하면 close 도중 예외가 발생하더라도 모든 자원의 close 호출을 보장한다.
        // [문제5 해결] try with resource 를 사용하면 핵심 예외가 부가예외로 가려지는 일은 발생하지 않는다.
        //              부가예외는 핵심예외에 담겨지는데 Throwable::getSuppressed() 로 참조할 수 있다.
        // [문제6 해결] try () 내부에서 선언한 자원의 역순으로 close 를 호출하므로 자원 정리 순서 실수가 없어지고
        //              finally 자체가 없어졌으므로 자원정리를 안하는 실수도 없어졌다.

        // 참고. try () 내부에서 자원을 굳이 생성하지 않더라도 변수를 선언만 하더라도 close 를 호출해주는 방식이다.
        //      피치못하게 아래와 같은 코드가 만들어지더라도 가능
        // Resource resource = new Resource();
        // ...
        // try (resource) { ... }
    }
}
