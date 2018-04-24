package lt.vu.mif.jate.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadDemo16 {

    public static void main(String[] args) throws InterruptedException {
        
        ScheduledExecutorService es = Executors.newScheduledThreadPool(2);
        
        es.scheduleWithFixedDelay(
            new Runnable() {
                @Override
                public void run() {

                    System.out.format("%02d: start #1%n", Thread.currentThread().getId());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) { }
                    System.out.format("%02d: end #1%n", Thread.currentThread().getId());
                    
                }
            }, 1, 1, TimeUnit.SECONDS);
        
        es.scheduleWithFixedDelay(
            new Runnable() {
                @Override
                public void run() {

                    System.out.format("%02d: start #2%n", Thread.currentThread().getId());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) { }
                    System.out.format("%02d: end #2%n", Thread.currentThread().getId());
                    
                }
            }, 0, 3300, TimeUnit.MILLISECONDS);
        
        TimeUnit.SECONDS.sleep(10);
        
        es.shutdown();
        
    }
    
}
