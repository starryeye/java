package auto_closeable.v1;

import auto_closeable.common.CallException;
import auto_closeable.common.CloseException;

public class ServiceV1 {

    /**
     * logic() 은 service 의 메인 비즈니스 로직이다. 외부 자원(ResourceV1) 을 사용하고 정리해야한다.
     *
     * resourceB 는 resourceA 를 참조하여 생성한 외부 자원이라 가정한다.
     * 따라서, resourceA 는 resourceB 보다 먼저 생성되었고,
     * 자원을 정리할 때는 resourceB 는 resourceA 보다 먼저 정리되어야한다.
     */

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

        // 자원 사용
        ResourceV1 resourceA = new ResourceV1("resourceA");
        ResourceV1 resourceB = new ResourceV1("resourceB");

        resourceA.call();
        resourceB.callEx(); // resourceB 자원을 사용하던 도중 예외가 발생하였다.

        System.out.println("자원 정리"); // [문제1] resourceB.callEx() 에서 예외가 발생하여, 자원 정리가 되지 못하는 문제가 발생하였다.
        resourceB.closeEx();
        resourceA.closeEx();
    }
}
