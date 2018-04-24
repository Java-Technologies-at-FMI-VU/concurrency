package lt.vu.mif.jate.thread;

import java.util.concurrent.TimeUnit;

class DaemonThread extends Thread {

    public DaemonThread() {
        setDaemon(false);
        setDaemon(true);
    }

    @Override
    public void run() {
        int count = 0;

        while (true) {
            
            System.out.format("Hi from %s #%d%n", isDaemon() ? "daemon" : "user", ++count);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) { }
        }
    }
}

public class ThreadDemo04_01 {

    public static void main(String[] args) {
        new DaemonThread().start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) { }

        System.out.println("Main Thread ending here");
    }

}
