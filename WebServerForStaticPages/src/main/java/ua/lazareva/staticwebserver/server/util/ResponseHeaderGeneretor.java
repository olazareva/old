package ua.lazareva.staticwebserver.server.util;

import ua.lazareva.staticwebserver.file.ResourceReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class ResponseHeaderGeneretor {
    private final static String OK = "HTTP/1.1 200 OK";
    private final static String NOT_FOUND = "HTTP/1.1 404 Not Found";

    public static String generate(String url, boolean error) throws IOException {
        String responseHeader;
        responseHeader = error ? NOT_FOUND : OK + "\n";
        responseHeader += generateHeaders(url);
        System.out.println("\nGenerated response header = " + responseHeader);
        return responseHeader;
    }

    private static String generateHeaders(String url) throws IOException {
        String resourcePath = ResourceReader.getResourcePath();
        File file = new File(resourcePath + File.separator + url);

        String responseHeader = "Content-Type: " + Files.probeContentType(file.toPath()) + "\n";
        responseHeader += "Content-Length: " + Files.size(file.toPath()) + "\n";
        responseHeader += "Date: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "\n";
        return responseHeader + "\n";
    }
}
