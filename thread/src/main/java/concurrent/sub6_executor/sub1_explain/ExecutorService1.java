package concurrent.sub6_executor.sub1_explain;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface ExecutorService1 {

    /**
     * Java 19 이상에서는 ExecutorService 는 기존 Executor interface 상속 외에 AutoCloseable 도 상속한다.
     */

    <T> Future<T> submit(Callable<T> task); // Callable 은 반환 값이 존재하는 Runnable 역할의 함수형 인터페이스이다.

    // default 메서드로 AutoCloseable 을 구현함
//    @Override
//    default void close() {...}
}
