package auto_closeable.v3;

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
            // [문제3] catch 구문을 수행한 다음 finally 구문이 실행되어 자원 정리가 늦어진다.

            if (resourceB != null) {
                try {
                    resourceB.closeEx(); // resourceB 자원을 정리하던 도중 예외가 발생하였다.
                } catch (CloseException e) {
                    // [문제4 해결] resourceB.closeEx 에서 예외가 발생하더라도 try-catch 로 예외를 처리해줘서 resoureA 자원을 정리할 수 있도록 하였다.
                    // [문제5 해결] 자원 정리 시점에 발생한 예외를 즉시 처리했기 때문에, 핵심 예외가 부가 예외로 가려지지 않는다.
                    System.out.println("close ex: " + e); // close 예외는 당장 해결할 수 없는 경우가 많으므로 버리고 로깅만 한다.
                }
            }
            if (resourceA != null) {
                try {
                    resourceA.closeEx();
                } catch (CloseException e) {
                    System.out.println("close ex: " + e); // close 예외는 당장 해결할 수 없는 경우가 많으므로 버리고 로깅만 한다.
                }
            }

            // [문제6] 개발자는 resourceA, resourceB 자원정리 순서를 헷갈리거나 자원정리를 안해버리는 실수가 있을 수 있다.
        }

    }
}
