package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;

public class ThreadDemo09 {

    long simple_count = 0;
    volatile long volatile_count = 0;
     
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo09 d = new ThreadDemo09();
        
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10_000; j++) {
                    d.simple_count++;
                    d.volatile_count++;
                }
            });
        }
        
        long start = System.nanoTime();

        for (Thread t: threads) {
            t.start();
        }
                 
        for (Thread t: threads) {
            t.join();
        }

        System.out.println("Elapsed: " + (TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS)) + " ms");
        System.out.println("Simple count is: " + d.simple_count);
        System.out.println("Volatile count is: " + d.volatile_count);

    }

}
