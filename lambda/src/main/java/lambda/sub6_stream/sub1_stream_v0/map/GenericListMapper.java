package lambda.sub6_stream.sub1_stream_v0.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class GenericListMapper {

    private GenericListMapper() {}

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> result = new ArrayList<>();

        for (T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}
