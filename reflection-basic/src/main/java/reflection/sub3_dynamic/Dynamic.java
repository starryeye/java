package reflection.sub3_dynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Dynamic {

    /**
     * reflection 에서 "동적" 이라는 단어의 의미를 알아본다.
     *
     * 사용자 입력에 따라 두 개의 숫자를 더하거나 빼거나 할 수 있는 계산기 프로그램을 만든다고 생각해보자..
     * 그러면 일반적으로는..
     * 사용자가 "sub" or "add" 문자열을 입력함에 따라 if 문으로 코드를 분기하여
     * calculator 인스턴스의 sub() or add() 메서드를 호출하도록 개발할 것이다.
     * -> 사용자의 입력에 따라 다른 코드가 실행되는 것이다.
     * -> 코드의 흐름이 "정적" 이다.
     * -> 실행되는 코드 라인에 따라 호출되는 메서드가 정해짐
     *
     * reflection 기술을 이용한다면..
     * 사용자가 "sub" or "add" 문자열을 입력해도 항상 코드 실행 라인이 동일하다.
     * 사용자가 입력한 문자열이 매번 다르더라도, 실행되는 코드라인은 동일하지만 호출되는 메서드는 달라짐
     * -> 사용자의 입력이 매번 달라도 동일한 코드가 실행됨
     * -> 코드의 흐름이 "동적" 이다.
     * -> 실행되는 코드 라인은 동일하지만, 호출되는 메서드가 다를 수 있음
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




        // 호출할 메서드(연산자)를 변수 이름으로 "동적"으로 선택
        // sub, add
        System.out.print("호출 메서드: ");
        String methodName = scanner.nextLine();
        scanner.close();
        Class<? extends Calculator> aClass = Calculator.class;
        Method method = aClass.getMethod(methodName, int.class, int.class);


        // 동적으로 선택된 메서드를 호출
        Object returnValue = method.invoke(calculator, num1, num2);
        System.out.println("returnValue = " + returnValue);
    }
}
