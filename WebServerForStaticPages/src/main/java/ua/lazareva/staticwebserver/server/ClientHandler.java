package ua.lazareva.staticwebserver.server;

import ua.lazareva.staticwebserver.file.ResourceReader;
import ua.lazareva.staticwebserver.model.Request;
import ua.lazareva.staticwebserver.server.util.RequestParser;
import ua.lazareva.staticwebserver.server.util.ResponseHeaderGeneretor;

import java.io.*;
import java.net.URLConnection;

public class ClientHandler implements Runnable {
    private BufferedInputStream inputStream;
    private BufferedOutputStream outputStream;
    private ResourceReader resourceReader;

    public ClientHandler(BufferedInputStream inputStream, BufferedOutputStream outputStream, ResourceReader resourceReader) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.resourceReader = resourceReader;
    }

    public void handle() {
        try (BufferedOutputStream outputStream = this.outputStream) {
            Request request = RequestParser.toRequest(inputStream);
            String url = request.getUrl();
            try {
                InputStream resource = resourceReader.getResource(url);
                if (resource.available() != -1) {
                    outputStream.write(ResponseHeaderGeneretor.generate(url, false).getBytes());
                    byte[] buffer = new byte[1024 * 64];
                    int length;
                    while ((length = resource.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                } else {
                    outputStream.write(ResponseHeaderGeneretor.generate(url, true).getBytes());
                }
            } catch (Exception e) {
                outputStream.write(ResponseHeaderGeneretor.generate(url, true).getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        handle();
    }
}