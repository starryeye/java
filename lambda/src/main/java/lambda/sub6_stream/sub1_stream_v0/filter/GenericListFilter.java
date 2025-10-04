package lambda.sub6_stream.sub1_stream_v0.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class GenericListFilter {

    private GenericListFilter() {}

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }
}
