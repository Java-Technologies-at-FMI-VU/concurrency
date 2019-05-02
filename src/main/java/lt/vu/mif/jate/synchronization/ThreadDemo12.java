package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;

public class ThreadDemo12 implements Runnable {
   
    private final ThreadLocal<Integer> c = new ThreadLocal<>();
    private final Integer init_c;
    
    public ThreadDemo12(Integer init_c) {
        this.init_c = init_c;
    }
    
    @Override
    public void run() {
        
        this.c.set(init_c);
        
        for (int i = 0; i < 10; i++) {
            
            System.out.format("%s: c = %d%n", Thread.currentThread().getName(), c.get());
            c.set(c.get() + 1);
            
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {}
            
        }
    }
    
    public static void main(String[] args) {
        
        Runnable run = new ThreadDemo12(0);
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        
        t1.start();
        t2.start();
        
    }
    
}
