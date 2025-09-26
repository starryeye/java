package dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpRequestV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpResponseV4;

import java.io.IOException;

public interface HttpServletV4 {

    void service(HttpRequestV4 request, HttpResponseV4 response) throws IOException;
}
