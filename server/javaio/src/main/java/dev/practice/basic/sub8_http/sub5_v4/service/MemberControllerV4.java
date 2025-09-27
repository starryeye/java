package dev.practice.basic.sub8_http.sub5_v4.service;

import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpRequestV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.HttpResponseV4;
import dev.practice.basic.sub8_http.sub5_v4.http_server_library.Mapping;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class MemberControllerV4 {

    @Mapping("/form-add-member")
    public void addMemberForm(HttpResponseV4 response) {
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<body><html>\n")
                .append("<h1>Add New Member</h1>\n")
                .append("<form method='POST' action='/add-member'>\n")
                .append("ID: <input type='text' name='id'><br>\n")
                .append("Name: <input type='text' name='name'><br>\n")
                .append("Age: <input type='text', name='age'><br>\n")
                .append("<input type='submit' value='Add'>\n")
                .append("</form>\n")
                .append("<a href='/'>Back to Home</a>\n")
                .append("</body></html>\n");

        response.writeBody(responseBody.toString());
    }

    @Mapping("/add-member")
    public void addMember(HttpRequestV4 request, HttpResponseV4 response) {

        String id = request.getQueryParameter("id");
        String name = request.getQueryParameter("name");
        int age = Integer.parseInt(request.getQueryParameter("age"));

        threadLog("add member success, id = " + id + ", name = " + name + ", age = " + age);

        String responseBody = "<h1>save ok</h1>\n" +
                "id: " + id + "<br>\n" +
                "name: " + name + "<br>\n" +
                "age: " + age + "<br>\n" +
                "<a href='/'>Back to Home</a>";

        response.writeBody(responseBody);
    }
}
