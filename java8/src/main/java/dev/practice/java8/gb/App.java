package dev.practice.java8.gb;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class App {

    /**
     * 병렬로 정렬해보기
     */

    public static void main(String[] args) {


        int size = 20000;
        int[] numbers = new int[size];

        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt()); //무작위 배열 생성


        // 직렬 정렬
        long start = System.nanoTime();
        Arrays.sort(numbers); // 퀵소트, 스레드 1개, O(NlogN)
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        // --

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt()); //무작위 배열 생성

        // 병렬 정렬
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // 퀵소트, 스레드 여러개(ForkJoinPool), O(NlogN)
        System.out.println("parallel sorting took " + (System.nanoTime() - start));


        /**
         * CPU, input(배열크기) 자원들에 따라 달라질 수 있다.
         *
         * 병렬이 빠르던지.. 직렬이 빠르던지..
         */
    }
}
