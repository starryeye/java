package auto_closeable.common;

public class CallException extends Exception {

    /**
     * checked exception..
     * try-catch 로 잡아서 처리를 하거나 throws 키워드로 명시해줘야함.
     */

    public CallException(String message) {
        super(message);
    }
}
