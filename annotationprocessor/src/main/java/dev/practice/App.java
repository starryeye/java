package dev.practice;

public class App {

    /**
     * IDE 에서 Enable annotation processing 을 활성화 하는 것은..
     * 우리가 개발한 소스코드 뿐만아니라..
     * "컴파일타임에 생성될" 코드도 인식을해서 코딩할 수 있도록 해주는 것이다.
     * -> Project Structure > Modules > Excludes 에 존재하는 생성된 소스코드를
     * Sources 로 마크 해줄 필요가 있을 수 있다.
     *
     * gradle 에서 방법을 찾아야한다...
     */

    public static void main(String[] args) {

        /**
         * annotation processing 을 통해서 생성해낼 클래스가 MagicMoja 이다.
         */
        Moja moja = new MagicMoja();

        System.out.println(moja.pullOut());
    }
}
