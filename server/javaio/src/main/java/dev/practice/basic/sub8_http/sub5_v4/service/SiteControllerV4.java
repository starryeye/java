package dev.practice.basic.sub8_http.sub5_v4.service;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpRequestV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpResponseV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.Mapping;

public class SiteControllerV4 {

    @Mapping("/site1")
    public void site1(HttpResponseV4 response) {
        response.writeBody("<h1>site1</h1>");
    }

    @Mapping("/site2")
    public void site2(HttpResponseV4 response) {
        response.writeBody("<h1>site2</h1>");
    }
}
