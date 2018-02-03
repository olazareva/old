package ua.lazareva.staticwebserver.server.util;

import ua.lazareva.staticwebserver.model.HttpMethod;
import ua.lazareva.staticwebserver.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestParser {

    public static Request toRequest(InputStream inputStream) throws IOException {
        Request request = new Request();
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
        injectUrlAndMethod(request, bufferedReader );
        injectHeaders(request, bufferedReader );
        System.out.println("Parsed request: " + request);
        return request;
    }

    private static void injectUrlAndMethod(Request request, BufferedReader reader) throws IOException {
        String str = reader.readLine();
        String[] methodAndUrl = str.split(" ");
        request.setHttpMethod(HttpMethod.getMethod(methodAndUrl[0]));
        request.setUrl(methodAndUrl[1]);
    }

    private static void injectHeaders(Request request, BufferedReader reader) throws IOException {
        String str;
        while (reader.ready()&&!(str = reader.readLine()).isEmpty()) {
            String[] nameAndValue = str.split(":");
            request.addHeader(nameAndValue[0], nameAndValue[1]);
        }
    }
}
