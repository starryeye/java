package dev.practice.basic.sub8_http.sub5_v4.http_server_library.servlet;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpRequestV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpResponseV4;

import java.io.IOException;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class DiscardServletV4 implements HttpServletV4 {
    @Override
    public void service(HttpRequestV4 request, HttpResponseV4 response) throws IOException {

        threadLog("discard request");
    }
}
