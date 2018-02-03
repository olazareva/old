package ua.lazareva.consolelogger;

import java.io.IOException;

public class Main {
    public static void main(String[] arg) throws IOException, InterruptedException {
        CommonArray commonArray = new CommonArray();

        Thread readThreat = new Thread(new ConsoleReader(commonArray));
        Thread logThreat = new Thread(new ConsoleLogger(commonArray));
        readThreat.start();
        logThreat.start();
        readThreat.join();
        logThreat.join();

    }
}
