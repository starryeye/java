package dev.practice.basic.sub8_http.sub5_v4;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpServerV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpServletContainerV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet.DiscardServletV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet.DispatcherServletV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet.HttpServletV4;
import dev.practice.basic.sub8_http.sub5_v4.service.HomeControllerV4;
import dev.practice.basic.sub8_http.sub5_v4.service.MemberControllerV4;
import dev.practice.basic.sub8_http.sub5_v4.service.SearchControllerV4;
import dev.practice.basic.sub8_http.sub5_v4.service.SiteControllerV4;

import java.io.IOException;
import java.util.List;

public class ServerMainV4 {

    /**
     * v3 와 비교하여..
     *      annotation + reflection 을 사용하였고 spring web mvc 의 주요 class 명으로 refactoring..
     *      form controller 추가
     *      HttpRequestV4 body 파싱 추가 (x-www-form-urlencoded)
     *          기존에는 HTTP request body 를 처리하지 않았음
     *
     * todo, 해보면 좋을 것들..
     *      annotation + reflection 적용한 ControllerContainer
     */

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {

        HttpServerV4 server = configureServer();
        server.start();
    }

    // configuration, 구성 영역
    private static HttpServerV4 configureServer() {
        List<Object> controllers = List.of(
                new HomeControllerV4(),
                new SiteControllerV4(),
                new SearchControllerV4(),
                new MemberControllerV4()
        );

        HttpServletV4 dispatcherServlet = new DispatcherServletV4(controllers);

        HttpServletContainerV4 servletContainer = new HttpServletContainerV4();
        servletContainer.setDefaultServlet(dispatcherServlet);
        servletContainer.add("/favicon.ico", new DiscardServletV4());

        return new HttpServerV4(SERVER_PORT, servletContainer);
    }
}
