package ua.lazareva.consolelogger;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private Scanner scanner = new Scanner(System.in);
    ArrayList<String> array;

    public ConsoleReader(CommonArray commonArray) {
        array = commonArray.array;
    }

    private void readConsoleToArrayList() {
        String str = scanner.nextLine();
        array.add(str);

}

    @Override
    public void run() {
        while (true) {
            readConsoleToArrayList();
        }

    }
}
