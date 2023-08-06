package dev.practice.java8.ga.b;

import java.util.Arrays;

@Chicken("양념")
@Chicken("후라이드")
public class App {

    public static void main(String[] args) {


        // annotation 값 가져오기 방법 1
        Chicken[] annotationsByType = App.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(annotationsByType)
                .forEach(chicken -> System.out.println(chicken.value()));

        // annotation 값 가져오기 방법 2
        ChickenContainer container = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(container.value())
                .forEach(chicken -> System.out.println(chicken.value()));
    }
}
