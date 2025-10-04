package lambda.sub6_stream.sub3_stream_v1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStreamV1<T> {

    private List<T> internalList;

    private MyStreamV1(List<T> internalList) {
        this.internalList = internalList;
    }

    // Static Factory Method Pattern (정적 팩토리 메서드 패턴)
    public static <T> MyStreamV1<T> of(List<T> internalList) {
        return new MyStreamV1<>(internalList);
    }

    public MyStreamV1<T> filter(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (T t : internalList) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return MyStreamV1.of(result);
    }

    public <R> MyStreamV1<R> map(Function<T, R> mapper) {
        List<R> result = new ArrayList<>();

        for (T t : internalList) {
            result.add(mapper.apply(t));
        }

        return MyStreamV1.of(result);
    }

    public List<T> toList() {
        return internalList;
    }

    public void forEach(Consumer<T> consumer) {
        for (T t : internalList) {
            consumer.accept(t);
        }
    }
}
