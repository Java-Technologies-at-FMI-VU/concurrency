package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;

public class ThreadDemo08 {

    int sync_count = 0;
    int unsync_count = 0;
     
    synchronized void sync_increment() {
        sync_count++;
    }
    
    void unsync_increment() {
        unsync_count++;
    }
     
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo08 d = new ThreadDemo08();
        
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10_000; j++) {
                    d.sync_increment();
                    d.unsync_increment();
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
        System.out.println("Synchronized count is: " + d.sync_count);
        System.out.println("Unsynchronized count is: " + d.unsync_count);
    }

}
