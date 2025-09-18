package dev.practice.basic.sub8_http.sub4_v3.service;

import dev.practice.basic.sub8_http.sub4_v3.http_server_library.HttpRequestV3;
import dev.practice.basic.sub8_http.sub4_v3.http_server_library.HttpResponseV3;
import dev.practice.basic.sub8_http.sub4_v3.http_server_library.HttpServletV3;

import java.io.IOException;

public class HomeServletV3 implements HttpServletV3 {
    @Override
    public void service(HttpRequestV3 request, HttpResponseV3 response) throws IOException {

        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<h1>home</h1>\n")
                .append("<ul>\n")
                .append("<li><a href='/site1'>site1</a></li>\n")
                .append("<li><a href='/site2'>site2</a></li>\n")
                .append("<li><a href='/search1?q=hello'>검색1</a></li>\n")
                .append("<li><a href='/search2?q=가나다'>검색2</a></li>\n")
                .append("</ul>");

        response.writeBody(responseBody.toString());
    }
}
