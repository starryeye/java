package reflection.sub3_dynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Dynamic {

    /**
     * Java Reflection 에서 "동적"이라는 단어의 의미를 알아본다.
     *
     * Reflection 은 컴파일 타임에 고정된 코드 흐름(정적)을 넘어서..
     * 런타임에 클래스, 메서드, 필드 등을 동적으로 검사하고 호출할 수 있게 한다.
     *
     * 사용자 입력에 따라 두 개의 숫자를 더하거나 빼거나 할 수 있는 계산기 프로그램을 만든다고 생각해보자..
     * 이 예제에서는 사용자가 입력한 문자열("add" 또는 "sub")에 따라 Calculator 클래스의 메서드를 런타임에 동적으로 호출하고있다.
     * 1. 일반적인 "정적" 방식으로 개발한다면..
     *      사용자가 "add" 또는 "sub"를 입력하면 if-else 문으로 분기하여 고정된 메서드(add() 또는 sub())를 호출되도록 개발..
     *      실행되는 코드 라인에 따라 호출되는 메서드는 컴파일 시점에 정해진다.
     *      코드 흐름이 고정되어 있어 유연성이 제한됨.
     * 2. Reflection 을 사용한 "동적" 방식으로 개발한다면..
     *      사용자가 입력한 문자열을 기반으로 런타임에 메서드를 동적으로 선택(calculatorClassMetadata.getMethod(methodName))
     *      사용자의 입력값이 무엇이라도 동일한 코드 라인이 실행되지만, 런타임에 사용자의 입력에 따라 다른 메서드가 호출됨. (런타임에 실행되는 코드라인은 동일하지만 호출되는 메서드는 달라짐)
     *      Java 의 정적 타입 시스템을 넘어, 런타임에 유연하게 동작하는 "동적" 특성을 보여준다.
     *
     * Spring 과의 연관성:
     * - Spring 의 의존성 주입(DI)은 Reflection 을 사용해 런타임에 빈을 동적으로 생성하고 주입 (@Component, @Autowired)
     * - Spring AOP 는 런타임에 프록시 객체를 동적으로 생성하여 메서드 호출을 가로챈다.(JDK Dynamic Proxy, CGLIB)
     *
     * 주의점:
     * - Reflection 은 런타임 유연성을 제공하지만, 성능 오버헤드와 타입 안전성 문제가 있음.
     */

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();



        // 연산 하고 싶은 두개의 숫자를 입력
        System.out.print("숫자1: ");
        int num1 = scanner.nextInt();
        System.out.print("숫자2: ");
        int num2 = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기




        // 사용자가 호출하고 싶은 메서드 이름을 입력 (예: "add" 또는 "sub")
        // 런타임에 동적으로 결정됨
        System.out.print("호출 메서드: ");
        String methodName = scanner.nextLine();
        scanner.close();

        // Reflection을 사용해 Calculator 클래스의 메타데이터를 동적으로 가져옴
        // 컴파일 타임에는 어떤 메서드가 호출될지 모름
        Class<? extends Calculator> calculatorClassMetadata = Calculator.class;

        // 사용자가 입력한 메서드 이름을 기반으로 런타임에 메서드를 동적으로 조회
        // getMethod는 public 메서드를 찾으며, 메서드 시그니처(이름, 파라미터 타입)를 확인
        Method method = calculatorClassMetadata.getMethod(methodName, int.class, int.class);


        // 동적으로 선택된 메서드를 런타임에 호출
        // 컴파일 타임에는 호출될 메서드가 고정되지 않고, 런타임 입력에 따라 달라짐 -> "동적"
        Object returnValue = method.invoke(calculator, num1, num2);
        System.out.println("returnValue = " + returnValue);
    }
}
