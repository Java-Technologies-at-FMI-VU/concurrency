package lt.vu.mif.jate.synchronization;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
 
public class ThreadDemo13_01 {
    
    private static final ReentrantLock LOCK = new ReentrantLock();
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
            
            COUNTER.incrementAndGet();
            print("AcquireLockRunnable", "try lock");
            
            LOCK.lock();
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
                
                LOCK.unlock();
                print("AcquireLockRunnable", "unlocked");
                
            }
        }
 
    }
 
}