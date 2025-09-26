package annotation.sub5_practice.handler_mapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleHandlerMapping {

    /**
     * Java annotation + reflection 을 응용하여
     * 어떤 요청의 path 정보와 특정 controller 를 매핑 해주는데 사용할 수 있다.
     *
     * 아래 예제에서는 단순히 mapping 될 수 있는 controller 들을 모두 출력하고 해당 controller 를 호출해보았다.
     */

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        SimpleController simpleController = new SimpleController();

        Class<? extends SimpleController> simpleControllerClassMetadata = simpleController.getClass();
        for (Method method : simpleControllerClassMetadata.getDeclaredMethods()) {

            if (method.isAnnotationPresent(SimpleMapping.class)) {
                SimpleMapping annotation = method.getAnnotation(SimpleMapping.class);
                String value = annotation.value();

                System.out.println("This request was mapped to path " + value);
                method.invoke(simpleController);
            }
        }
    }
}
