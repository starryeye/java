package dev.practice.basic.sub8_http.sub5_v4.service;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpRequestV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpResponseV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.Mapping;

public class HomeControllerV4 {

    @Mapping("/")
    public void home(HttpResponseV4 response) {
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<h1>home</h1>\n")
                .append("<ul>\n")
                .append("<li><a href='/site1'>site1</a></li>\n")
                .append("<li><a href='/site2'>site2</a></li>\n")
                .append("<li><a href='/search?q=hello'>\"hello\" 검색</a></li>\n")
                .append("<li><a href='/search?q=가나다'>\"가나다\" 검색</a></li>\n")
                .append("</ul>");

        response.writeBody(responseBody.toString());
    }
}
