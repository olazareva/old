package ua.lazareva.consolelogger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ConsoleLogger implements Runnable {
    ArrayList<String> array;
    FileWriter fileWriter = new FileWriter("src\\ua\\lazareva\\threads\\consolelogger\\ConsoleLog.txt", false);

    public ConsoleLogger(CommonArray commonArray) throws IOException {
        array = commonArray.array;
    }

    private void writeToFile() throws IOException {
        for (String s : array) {
            fileWriter.write(s);
            fileWriter.write('\n');
        }
        fileWriter.flush();
    }

    @Override
    public void run() {
        try {
            while (true) {
                writeToFile();
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
