package arraylist.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListClear {

    @Test
    @DisplayName("clear()로 리스트를 비울 수 있다")
    void clearList() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        list.clear();

        assertThat(list).isEmpty();
    }
}
