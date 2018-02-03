package ua.lazareva.staticwebserver.server;

import org.junit.Test;

public class ServerITest {

    @Test
    public void testServer(){
        Server server = new Server(8080);
        server.setResourcePath("src/test/resources/webapp");
        server.start();
    }

}