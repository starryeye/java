package collection.arraylist.declaration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListDeclaration {

    @Test
    @DisplayName("기본 생성자로 ArrayList 를 생성할 수 있다")
    void defaultConstructor() {

        ArrayList<String> arrayList = new ArrayList<>();

        assertThat(arrayList).isEmpty();
    }

    @Test
    @DisplayName("초기 용량을 지정해 ArrayList 를 생성할 수 있다")
    void withInitialCapacity() {

        ArrayList<Integer> arrayList = new ArrayList<>(100);

        assertThat(arrayList).isEmpty();
    }
}
