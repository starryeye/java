package dev.practice.basic.sub8_http.sub4_v3.http_server_library;

import java.io.IOException;

public interface HttpServletV3 {

    void service(HttpRequestV3 request, HttpResponseV3 response) throws IOException;
}
