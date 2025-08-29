package auto_closeable.v2;

import auto_closeable.common.CallException;
import auto_closeable.common.CloseException;

public class ServiceV2 {

    public static void main(String[] args) {

        try {

            logic(); // 비즈니스 로직

        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            // [문제5]
            // 비즈니스 로직을 수행하던 도중 발생한 예외는 resourceB.callEx 에 의한 예외이지만..
            // 실제 처리되는 예외는 resourceB.closeEx 에 의한 예외이다.
            // finally 에서 핵심 예외가 부가 예외로 가려지는 문제가 발생한다.
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {

        // [문제2] finally 영역에서 resourceA, resourceB 참조 변수를 사용해야하므로 try 영역 외부로 빼야한다.
        ResourceV1 resourceA = null;
        ResourceV1 resourceB = null;


        try {
            resourceA = new ResourceV1("resourceA"); // ResourceV1 생성 도중에도 예외가 발생할 수 있다고 가정하여 try 내부로 넣음
            resourceB = new ResourceV1("resourceB");

            resourceA.call();
            resourceB.callEx(); // resourceB 자원을 사용하던 도중 예외가 발생하였다.

        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            // [문제1 해결] resourceB.callEx 가 발생하여도 finally 에서 자원 정리를 처리할 수 있도록 하였다.
            // [문제3] catch 구문을 수행한 다음 finally 구문이 실행되어 자원 정리가 늦어진다.

            if (resourceB != null) { // 자원 생성 도중 예외가 발생하면 참조 변수가 null 일 수 있으므로 if 문으로 보호한다.
                resourceB.closeEx(); // resourceB 자원을 정리하던 도중 예외가 발생하였다.
            }
            if (resourceA != null) {
                resourceA.closeEx(); // [문제4] resourceB.closeEx() 에서 예외가 발생하여 resourceA 는 자원정리 되지 못하는 문제 발생
            }
        }

    }
}
