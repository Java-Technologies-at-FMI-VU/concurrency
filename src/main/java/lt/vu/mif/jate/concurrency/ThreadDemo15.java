package lt.vu.mif.jate.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadDemo15 {

    public static void main(String[] args) throws InterruptedException {
        
        ExecutorService es = Executors.newFixedThreadPool(10);
        
        for (int i = 0; i < 20; i++) {
            final int n = i;
            es.execute(
                new Runnable() {
                    @Override
                    public void run() {

                        System.out.format("%02d: start #%02d%n", Thread.currentThread().getId(), n);

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException ex) { }

                        System.out.format("%02d: end   #%02d%n", Thread.currentThread().getId(), n);
                    }
                }
            );
        }
        
        es.shutdown();
        
    }
    
}
