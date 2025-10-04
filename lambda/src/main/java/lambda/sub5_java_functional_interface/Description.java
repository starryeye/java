package lambda.sub5_java_functional_interface;

public interface Description {

    /**
     * Java 가 기본적으로 제공하는 함수형 인터페이스에 대해 알아본다.
     *
     * 기본형
     *      java.util.function
     *          Function
     *              @FunctionalInterface
     *              public interface Function<T, R> {
     *                  R apply(T t);
     *              }
     *              하나의 매개변수를 받고 결과를 반환
     *          Consumer
     *              @FunctionalInterface
     *              public interface Consumer<T> {
     *                  void accept(T t);
     *              }
     *              하나의 매개변수를 받고 결과를 반환하지 않음
     *          Supplier
     *              @FunctionalInterface
     *              public interface Supplier<T> {
     *                  T get();
     *              }
     *              매개변수를 받지 않고 결과를 반환
     *          Runnable
     *              @FunctionalInterface
     *              public interface Runnable {
     *                  void run();
     *              }
     *              하위 호환성을 위해 java.lang 에 존재한다.
     *              매개변수를 받지 않고 결과도 반환하지 않음
     *
     * 특화형
     *      기본형에서 의도를 명확하게 하여 개발자로 하여금 가독성을 높인 함수형 인터페이스이다.
     *      java.util.function
     *          Predicate
     *              @FunctionalInterface
     *              public interface Predicate<T> {
     *                  boolean test(T t);
     *              }
     *              하나의 매개변수를 받고 boolean 타입의 결과를 반환
     *              조건 검사 의도에 특화
     *          UnaryOperator
     *              @FunctionalInterface
     *              public interface UnaryOperator<T> extends Function<T, T> {}
     *              Function 을 상속받아서 하나의 매개변수 타입과 결과 타입이 동일한 의도에 특화
     *          BinaryOperator
     *              @FunctionalInterface
     *              public interface BinaryOperator<T> extends BiFunction<T,T,T> {}
     *              BiFunction 을 상속받아서 두개의 매개변수 타입과 결과 타입이 동일한 의도에 특화
     *
     * 기본형(primitive) 특화
     *      java.util.function
     *          IntFunction
     *              @FunctionalInterface
     *              public interface IntFunction<R> {
     *                  R apply(int value);
     *              }
     *              auto-boxing/unboxing 의 성능 비용을 줄이고 제네릭이 primitive type 을 다룰 수 없음을 극복하기 위한 특화
     *              모든 primitive type 을 prefix 로 하여 기본형 함수형 인터페이스를 붙이는 네이밍으로 존재한다.
     *                  IntFunction, IntConsumer, ..., DoubleSupplier, LongXXXXX, ....
     *
     * 확장?
     *      Callable
     *          @FunctionalInterface
     *          public interface Callable<V> {
     *              V call() throws Exception;
     *          }
     *          Supplier 와 비슷한데 예외를 던질 수 있도록 만들어짐 (Checked exception 외부 던지기용)
     *              Callable 이 만들어진 이유 자체는, ExecutorService 이용시 Runnable 의 반환 값이 없는 것을 개선하기 위해 Future 와 함께 Callable 을 사용하기위해 만들어짐
     *          하위 호환성을 위해 java.util.concurrent 패키지에 존재
     */
}
