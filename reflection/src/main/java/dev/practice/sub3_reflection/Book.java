package dev.practice.sub3_reflection;

public class Book {

    public static String A = "A";

    private String B = "B";

    public Book() {
    }

    private Book(String b) {
        this.B = b;
    }

    private void c() {
        System.out.println("C");
    }

    public int sum(int left, int right) {
        return left + right;
    }
}
