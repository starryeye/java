package dev.practice.java8.subject2_디폴트메서드.a_디폴트메서드;

public class FooImpl implements Foo{

    String name;

    public FooImpl(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

//    @Override
//    public void printNameUpperCase() {
//        System.out.println(this.name.toUpperCase());
//    }
}
