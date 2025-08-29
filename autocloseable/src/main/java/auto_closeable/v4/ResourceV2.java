package auto_closeable.v4;

import auto_closeable.common.CallException;
import auto_closeable.common.CloseException;

public class ResourceV2 implements AutoCloseable {

    /**
     * AutoCloseable 인터페이스를 구현하였다.
     */

    private String name;

    public ResourceV2(String name) {
        this.name = name;
    }

    public void call() {
        System.out.println(name + " call");
    }

    public void callEx() throws CallException {
        System.out.println(name + " callEx");
        throw new CallException(name + " ex");
    }

    /**
     * AutoCloseable::close 메서드는 throws Exception 을 지원하고 있기 때문에
     * CloseException 을 던지는 오버라이딩이 가능하다.
     *
     * close 를 호출하면 CloseException 이 항상 발생
     */
    @Override
    public void close() throws CloseException {
        System.out.println(name + " close");
        throw new CloseException(name + " ex");
    }
}
