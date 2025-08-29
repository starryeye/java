package auto_closeable.v3;

import auto_closeable.common.CallException;
import auto_closeable.common.CloseException;

public class ResourceV1 {

    /**
     * Java 프로세스가 접근하는 어떤 외부 자원에 해당하는 클래스라고 가정한다.
     * call()
     *      이 자원(ResourceV1)에 대한 비즈니스 로직에 해당하며, 예외 발생하지 않고 정상 흐름
     * callEx()
     *      이 자원(ResourceV1)에 대한 비즈니스 로직에 해당하며, 비정상 흐름으로 CallException (checked exception) 예외 발생했고 던져진다고 가정
     * close()
     *      이 자원(ResourceV1)의 정리에 해당하며, 정상 종료
     * closeEx()
     *      이 자원(ResourceV1)의 정리에 해당하며, 비정상 종료로 CloseException (checked exception) 예외 발생했고 던져진다고 가정
     */

    private String name;

    public ResourceV1(String name) {
        this.name = name;
    }

    public void call() {
        System.out.println(name + " call");
    }

    public void callEx() throws CallException {
        System.out.println(name + " callEx");
        throw new CallException(name + " ex");
    }

    public void close() {
        System.out.println(name + " close");
    }

    public void closeEx() throws CloseException {
        System.out.println(name + " closeEx");
        throw new CloseException(name + " ex");
    }
}
