package ua.lazareva.timer;

public class Timer implements Runnable {
    private String name;
    private int counter;

    Timer(String name, int counter) {
        this.name = name;
        this.counter = counter;
    }

    public static void main(String[] arg) throws InterruptedException {

        Thread thread1 = new Thread(new Timer("firstTimer", 5));
        Thread thread2 = new Thread(new Timer("secondTimer", 10));
        Thread thread3 = new Thread(new Timer("thirdTimer", 5));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    @Override
    public void run() {

        for (int i = counter; i > 0; i--) {
            try {
                Thread.sleep(1000);
                System.out.println(name + " " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " " + "is finished the countdown");
    }
}
