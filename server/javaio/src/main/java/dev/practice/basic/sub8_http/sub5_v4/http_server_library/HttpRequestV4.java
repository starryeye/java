package dev.practice.basic.sub8_http.sub5_v4.http_server_library;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class HttpRequestV4 {

    private String method;
    private String path;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequestV4(BufferedReader reader) throws IOException {
        parseStartLine(reader);
        parseHeaders(reader);

        printParsedRequest(); // 요청 데이터 출력
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getQueryParameter(String key) {
        return queryParameters.get(key);
    }

    public String getHeader(String key) {
        return headers.get(key == null ? null : key.toLowerCase(Locale.ROOT));
    }

    // GET /search?q=hello HTTP/1.1
    private void parseStartLine(BufferedReader reader) throws IOException {

        String startLine = reader.readLine();
        if (startLine == null) {
            throw new IOException("EOF.. Unexpected end of stream..");
        }

        String[] startLineParts = startLine.split(" ");
        if (startLineParts.length != 3) {
            throw new IOException("Invalid start line: " + startLine);
        }

        // method
        method = startLineParts[0];

        // path
        String[] pathParts = startLineParts[1].split("\\?");
        path = pathParts[0];

        // query parameter
        if (pathParts.length > 1) {
            parseQueryParameters(pathParts[1]);
        }
    }

    private void parseHeaders(BufferedReader reader) throws IOException {

        String line;
        while ((line = reader.readLine()) != null) {

            if (line.isEmpty()) break; // 빈 줄이면 헤더 종료

            int idx = line.indexOf(':');
            if (idx <= 0) continue; // 잘못된 형식은 건너뜀

            String name = line.substring(0, idx).trim().toLowerCase(Locale.ROOT);
            String value = line.substring(idx + 1).trim();

            // 같은 이름이 여러 번 오면 마지막 값으로 덮어씀
            headers.put(name, value);
        }
    }

    private void parseQueryParameters(String queryString) {
        for (String queryParameter : queryString.split("&")) {
            String[] keyValue = queryParameter.split("=");
            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";

            queryParameters.put(key, value);
        }
    }

    public void printParsedRequest() {
        threadLog("Http request parsed..");
        threadLog("-------------------------------- HTTP request --------------------------------");
        System.out.print(this.toString());
        threadLog("------------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(method)
                .append(' ')
                .append(buildRequestTarget())
                .append(" HTTP/1.1\n");

        for (Map.Entry<String, String> e : headers.entrySet()) {
            sb.append(e.getKey()).append(": ").append(e.getValue()).append('\n');
        }

        sb.append('\n'); // 헤더 바디 구분

        return sb.toString();
    }

    // queryParameters 붙이기
    private String buildRequestTarget() {

        if (queryParameters.isEmpty()) return path;

        StringBuilder sb = new StringBuilder();
        boolean firstQueryParameterFlag = true;
        for (Map.Entry<String, String> e : queryParameters.entrySet()) {

            if (!firstQueryParameterFlag) sb.append('&');
            firstQueryParameterFlag = false;

            sb.append(URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8))
                    .append('=')
                    .append(URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8));
        }
        return path + "?" + sb;
    }
}
