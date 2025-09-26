package dev.practice.basic.sub8_http.sub5_v4.service;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpRequestV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpResponseV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.Mapping;

public class SearchControllerV4 {

    @Mapping("/search")
    public void search(HttpRequestV4 request, HttpResponseV4 response) {
        String query = request.getQueryParameter("q");

        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<h1>Search</h1>\n")
                .append("<ul>\n")
                .append("<li>query: ").append(query).append("</li>\n")
                .append("</ul>");

        response.writeBody(responseBody.toString());
    }
}
