package ua.lazareva.staticwebserver.server;

import ua.lazareva.staticwebserver.file.ResourceReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    private int port;
    private ResourceReader resourceReader;
    private ExecutorService service = new ThreadPoolExecutor(0, 15,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());

                ClientHandler clientHandler = new ClientHandler(inputStream, outputStream, resourceReader);
                service.submit(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setResourcePath(String resourcePath) {
        resourceReader = new ResourceReader(resourcePath);
    }
}
