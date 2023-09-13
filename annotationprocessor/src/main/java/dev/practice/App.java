package dev.practice;

public class App {

    public static void main(String[] args) {

        /**
         * annotation processing 을 통해서 생성해낼 클래스가 MagicMoja 이다.
         */
        Moja moja = new MagicMoja();

        System.out.println(moja.pullOut());
    }
}
