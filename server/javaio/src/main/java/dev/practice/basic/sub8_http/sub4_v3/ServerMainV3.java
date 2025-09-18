package dev.practice.basic.sub8_http.sub4_v3;

import dev.practice.basic.sub8_http.sub4_v3.http_server_library.HttpServerV3;
import dev.practice.basic.sub8_http.sub4_v3.http_server_library.HttpServletMappingV3;
import dev.practice.basic.sub8_http.sub4_v3.http_server_library.servlet.DiscardServletV3;
import dev.practice.basic.sub8_http.sub4_v3.service.HomeServletV3;
import dev.practice.basic.sub8_http.sub4_v3.service.SearchServletV3;
import dev.practice.basic.sub8_http.sub4_v3.service.Site1ServletV3;
import dev.practice.basic.sub8_http.sub4_v3.service.Site2ServletV3;

import java.io.IOException;

public class ServerMainV3 {

    /**
     * http_server_library 를 이용해서 Http 서버를 간편하게 만들 수 있다.
     */

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {

        HttpServletMappingV3 servletMapping = new HttpServletMappingV3();
        servletMapping.add("/", new HomeServletV3());
        servletMapping.add("/site1", new Site1ServletV3());
        servletMapping.add("/site2", new Site2ServletV3());
        servletMapping.add("/search1", new SearchServletV3());
        servletMapping.add("/search2", new SearchServletV3());
        servletMapping.add("/favicon.ico", new DiscardServletV3());

        HttpServerV3 server = new HttpServerV3(SERVER_PORT, servletMapping);
        server.start();
    }
}
