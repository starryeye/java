package array.declaration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayDeclaration {

    @Test
    void test1() {

        int[] intArr = new int[3];

        assertThat(intArr).hasSize(3);
        assertThat(intArr).containsExactly(0, 0, 0); // default value
    }

    @Test
    void test2() {

        int[] intArr = new int[]{1, 2, 3};

        assertThat(intArr).hasSize(3);
        assertThat(intArr).containsExactly(1, 2, 3);
    }

    @Test
    void test3() {

        int[] intArr = {1, 2, 3};

        assertThat(intArr).hasSize(3);
        assertThat(intArr).containsExactly(1, 2, 3);
    }
}
