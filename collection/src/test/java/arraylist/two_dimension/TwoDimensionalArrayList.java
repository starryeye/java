package arraylist.two_dimension;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TwoDimensionalArrayList {

    @Test
    void test() {

        // 2차원 ArrayList 선언
        List<List<Integer>> matrix = new ArrayList<>();

        int rows = 3;
        int cols = 4;

        // 초기화: 3행 4열, 값은 i * j로 설정
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(i * j);
            }
            matrix.add(row);
        }

        // 출력
        System.out.println("초기화된 2차원 리스트:");
        printMatrix(matrix);

        // 특정 값 접근
        int value = matrix.get(1).get(2); // 2행 3열
        System.out.println("\n2행 3열 (1, 2) 값: " + value);

        // 값 수정
        matrix.get(1).set(2, 999);
        System.out.println("\n2행 3열 값을 999로 수정한 후:");
        printMatrix(matrix);
    }

    private void printMatrix(List<List<Integer>> matrix) {
        for (List<Integer> row : matrix) {
            System.out.println(row);
        }
    }
}
