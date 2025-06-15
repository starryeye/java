package array.utils.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ToString {

    @DisplayName("Arrays.toString()")
    @Test
    void test() {
        int[] intArray = {1, 2, 3, 4, 5};

        System.out.println(Arrays.toString(intArray));
        System.out.println(intArray);
    }
}
