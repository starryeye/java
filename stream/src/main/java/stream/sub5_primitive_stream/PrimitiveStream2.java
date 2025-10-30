package stream.sub5_primitive_stream;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class PrimitiveStream2 {

    /**
     * 기본형 특화 스트림 연산자
     *      예제는 IntStream 으로 진행함
     *
     * 1. sum
     * 2. min
     * 3. max
     * 4. count
     * 5. average
     * 6. summaryStatics
     */

    public static void main(String[] args) {

        IntStream intStream = IntStream.range(1, 10);

        // sum
        int sum = IntStream.range(1, 10)
                .sum();
        System.out.println("sum = " + sum);


        // min
        OptionalInt min = IntStream.range(1, 10)
                .min();
        System.out.println("min = " + min);


        // max
        OptionalInt max = IntStream.range(1, 10)
                .max();
        System.out.println("max = " + max);


        // count
        long count = IntStream.range(1, 10)
                .count();
        System.out.println("count = " + count);


        // average
        OptionalDouble average = IntStream.range(1, 10)
                .average();
        System.out.println("average = " + average);



        // summaryStatics
        IntSummaryStatistics intSummaryStatistics = IntStream.range(1, 10)
                .summaryStatistics();
        System.out.println("summaryStatistics sum = " + intSummaryStatistics.getSum());
        System.out.println("summaryStatistics min = " + intSummaryStatistics.getMin());
        System.out.println("summaryStatistics max = " + intSummaryStatistics.getMax());
        System.out.println("summaryStatistics count = " + intSummaryStatistics.getCount());
        System.out.println("summaryStatistics average = " + intSummaryStatistics.getAverage());
    }
}
