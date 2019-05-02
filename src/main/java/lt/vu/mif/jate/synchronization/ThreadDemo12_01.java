package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo12_01 implements Runnable {

    static final AtomicInteger NEXT_ID = new AtomicInteger(1);
    
    final ThreadLocal<Integer> id = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return NEXT_ID.getAndIncrement();
        }   
    };
    final ThreadLocal<Long> prevTime = new ThreadLocal<>();
    
    @Override
    public void run() {
        prevTime.set(System.nanoTime());
        
        System.out.format("In %s id = %d%n", Thread.currentThread().getName(), id.get());
        
        for (int i = 0; i < 10; i++) {
            System.out.format("%d: elapsed %d%n", id.get(), System.nanoTime()- prevTime.get());
            prevTime.set(System.nanoTime());
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }
    
    public static void main(String[] args) {
        
        Runnable run = new ThreadDemo12_01();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        
        t1.start();
        t2.start();
        
    }
    
}
