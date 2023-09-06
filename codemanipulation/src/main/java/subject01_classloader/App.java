package subject01_classloader;

public class App {

    public static void main(String[] args) {

        ClassLoader classLoader = App.class.getClassLoader(); //App 클래스를 로딩한 클래스 로더를 반환
        System.out.println(classLoader);
        System.out.println(classLoader.getParent()); //클래스 로더는 상속관계를 가진다.
        System.out.println(classLoader.getParent().getParent()); // null 로 찍히지만.. 해당 클래스로더는 네이티브 코드로 되어 있어서 찍히지 않음 (최상위는 Bootstrap 로더이다.)
    }
}
