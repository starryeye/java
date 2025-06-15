package array.utils.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Stream {

    @DisplayName("Arrays.stream()")
    @Test
    void test1() {
        int[] arr = {1, 2, 3, 4, 5};

        IntStream stream = Arrays.stream(arr); // Arrays.stream() 을 사용하면 IntStream 반환

        stream.forEach(System.out::println);
    }
}
