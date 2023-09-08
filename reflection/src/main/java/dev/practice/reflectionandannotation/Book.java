package dev.practice.reflectionandannotation;

@MyAnnotation
@MyAnnotation2
public class Book {


    private static String B = "BOOK";
    private static final String C = "BOOK";

    @MyAnnotation2
    private String a = "a";

    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("f");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }
}
