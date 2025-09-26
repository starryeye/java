package dev.practice.basic.sub8_http.sub5_v4.http_server_library;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet.HttpServletV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet.InternalServerErrorServletV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet.NotFoundServletV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpServletContainerV4 {

    private final Map<String, HttpServletV4> servletMapping = new HashMap<>();

    private HttpServletV4 defaultServlet;
    private HttpServletV4 internalServerErrorServlet = new InternalServerErrorServletV4();

    public void add(String path, HttpServletV4 servlet) {
        servletMapping.put(path, servlet);
    }

    public void setDefaultServlet(HttpServletV4 servlet) {
        defaultServlet = servlet;
    }

    public void setInternalServerErrorServlet(HttpServletV4 servlet) {
        internalServerErrorServlet = servlet;
    }

    // Command pattern
    public void execute(HttpRequestV4 request, HttpResponseV4 response) throws IOException {

        try {

            // Null Object pattern
            HttpServletV4 servlet = servletMapping.getOrDefault(request.getPath(), defaultServlet);

            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            internalServerErrorServlet.service(request, response);
        }
    }

}
