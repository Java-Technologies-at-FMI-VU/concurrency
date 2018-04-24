package lt.vu.mif.jate.synchronization;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
 
public class ThreadDemo13_02 {
    
    private static final Semaphore LOCK = new Semaphore(2);
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
 
    public static void main(String[] args) throws InterruptedException {
        
        Thread firstThread = new Thread(new Action(), "FirstThread");
        firstThread.start();
        
        Thread.sleep(1000);
        
        for (int i = 2; i < 10; i++) {
            Thread t = new Thread(new Action(), "OtherThreads(" + (i - 1) + ")");
            t.start();
        }
        
    }
 
    private static void print(String tag, String p) {
        System.out.println(Thread.currentThread().getName() + ": " + tag + ": " + p);
    }
 
    private static class Action implements Runnable {
        
        @Override
        public void run() {
            
            try {
            
                COUNTER.incrementAndGet();
                print("AcquireLockRunnable", "try lock");

                LOCK.acquire();
                print("AcquireLockRunnable", "got lock");

                try {

                    if (COUNTER.get() == 1) {
                        Thread.sleep(5000);
                    } else {
                        Thread.sleep(1000);
                    }

                }  catch (InterruptedException e) {

                    e.printStackTrace(System.err);

                } finally {

                    LOCK.release();
                    print("AcquireLockRunnable", "unlocked");

                }
            
            } catch (InterruptedException ex) {}
            
        }
 
    }
 
}