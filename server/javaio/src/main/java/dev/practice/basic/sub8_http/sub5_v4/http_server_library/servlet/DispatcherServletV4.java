package dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpRequestV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpResponseV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.Mapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class DispatcherServletV4 implements HttpServletV4 {

    private final HashMap<String, RequestHandlerAdapter> requestHandlerMapping;

    private final HttpServletV4 delegate = new NotFoundServletV4();

    public DispatcherServletV4(List<Object> controllers) {
        this.requestHandlerMapping = new HashMap<>();
        initializePathMap(controllers);
    }

    // Command pattern
    @Override
    public void service(HttpRequestV4 request, HttpResponseV4 response) throws IOException {

        /**
         * ControllerAdvice 기능 만들려면 여기에 전체 try catch 걸고 하면 될듯..
         */
        String requestPath = request.getPath();

        // requestMappingHandlerMapping
        RequestHandlerAdapter requestHandlerAdapter = requestHandlerMapping.get(requestPath);

        if (requestHandlerAdapter == null) {
            delegate.service(request, response);
            return;
        }

        // requestMappingHandlerAdapter
        requestHandlerAdapter.invoke(request, response);
    }

    private void initializePathMap(List<Object> controllers) {

        // controller 의 모든 메서드를 순회 하며..
        // 요청이 왔을 때, path 를 key 로 호출될 controller 인스턴스와 메서드 메타정보를 HashMap 에 적재해놓는다.
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {

                if (method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    String path = annotation.value();

                    // 기 등록된 path 일 경우 중복으로 예외 발생 (중복 체크)
                    checkPathDuplication(path);

                    requestHandlerMapping.put(path, new RequestHandlerAdapter(method, controller));
                }
            }
        }
    }

    private void checkPathDuplication(String path) {
        if (requestHandlerMapping.containsKey(path)) {
            throw new RuntimeException("Duplicate path: " + path);
        }
    }


    private static class RequestHandlerAdapter {

        private final Method method;
        private final Object controllerInstance;

        public RequestHandlerAdapter(Method method, Object controllerInstance) {
            this.method = method;
            this.controllerInstance = controllerInstance;
        }

        public void invoke(HttpRequestV4 request, HttpResponseV4 response) {

            Object[] args = argumentResolve(request, response);

            try {
                method.invoke(controllerInstance, args);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        // argumentResolver
        private Object[] argumentResolve(HttpRequestV4 request, HttpResponseV4 response) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == HttpRequestV4.class) {
                    args[i] = request;
                } else if (parameterTypes[i] == HttpResponseV4.class) {
                    args[i] = response;
                } else {
                    throw new IllegalArgumentException("Unsupported parameter: " + parameterTypes[i]);
                }
            }
            return args;
        }
    }
}
