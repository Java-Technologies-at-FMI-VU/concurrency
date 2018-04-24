package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadDemo11 {

    AtomicLong count = new AtomicLong();
     
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo11 d = new ThreadDemo11();
        
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10_000; j++) {
                    d.count.incrementAndGet();
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
        System.out.println("Atomic count is: " + d.count.get());

    }

}
