package lambda.sub6_stream.sub1_v1;

import lambda.sub6_stream.sub1_v1.filter.GenericListFilter;
import lambda.sub6_stream.sub1_v1.map.GenericListMapper;

import java.util.List;

public class ExampleUsage1 {

    public static void main(String[] args) {

        runFilterExample();
        runMapExample();
    }

    private static void runFilterExample() {
        // 짝수 필터
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> integersFiltered = GenericListFilter.filter(integers, n -> n % 2 == 0);
        System.out.println("integersFiltered = " + integersFiltered);

        // 문자열 길이 필터
        List<String> strings = List.of("a", "bb", "ccc", "dddd", "eeeee");
        List<String> stringsFiltered = GenericListFilter.filter(strings, s -> s.length() >= 3);
        System.out.println("stringsFiltered = " + stringsFiltered);
    }

    private static void runMapExample() {
        // String -> String
        List<String> strings1 = List.of("a", "bb", "ccc", "dddd", "eeeee");
        List<String> upperMapped = GenericListMapper.map(strings1, s -> s.toUpperCase());
        System.out.println("upperMapped = " + upperMapped);

        // String -> Integer
        List<String> strings2 = List.of("a", "bb", "ccc", "dddd", "eeeee");
        List<Integer> lengthMapped = GenericListMapper.map(strings2, s -> s.length());
        System.out.println("lengthMapped = " + lengthMapped);

        // Integer -> String
        List<Integer> integers1 = List.of(1, 2, 3, 4, 5, 6);
        List<String> repeatMapped = GenericListMapper.map(integers1, n -> "*".repeat(n));
        System.out.println("repeatMapped = " + repeatMapped);
    }
}
