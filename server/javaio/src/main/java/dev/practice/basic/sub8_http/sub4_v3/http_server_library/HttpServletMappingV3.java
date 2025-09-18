package dev.practice.basic.sub8_http.sub4_v3.http_server_library;

import dev.practice.basic.sub8_http.sub4_v3.http_server_library.servlet.InternalServerErrorServletV3;
import dev.practice.basic.sub8_http.sub4_v3.http_server_library.servlet.NotFoundServletV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpServletMappingV3 {

    private final Map<String, HttpServletV3> servlets = new HashMap<>();

    private HttpServletV3 defaultServlet = new NotFoundServletV3();
    private HttpServletV3 internalServerErrorServlet = new InternalServerErrorServletV3();

    public void add(String path, HttpServletV3 servlet) {
        servlets.put(path, servlet);
    }

    public void setDefaultServlet(HttpServletV3 servlet) {
        defaultServlet = servlet;
    }

    public void setInternalServerErrorServlet(HttpServletV3 servlet) {
        internalServerErrorServlet = servlet;
    }

    // Command pattern
    public void execute(HttpRequestV3 request, HttpResponseV3 response) throws IOException {

        try {

            // Null Object pattern
            HttpServletV3 servlet = servlets.getOrDefault(request.getPath(), defaultServlet);

            servlet.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            internalServerErrorServlet.service(request, response);
        }
    }

}
