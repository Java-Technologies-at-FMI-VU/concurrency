package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;

public class ThreadDemo10 {

    private final Object lock01 = new Object();
    private final Object lock02 = new Object();
    
    static void print(String value) {
        System.out.println(Thread.currentThread().getName() + ":" + value);
    }
    
    void run01() {
        print("waiting for lock01");
        synchronized(lock01) {
            try {
                print("holding lock01");
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) { }
            run02();
        }
    }
    
    void run02() {
        print("waiting for lock02");
        synchronized(lock02) {
            try {
                print("holding lock02");
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) { }
            run01();
        }
    }
    
    public static void main(String[] args) {
        
        ThreadDemo10 d = new ThreadDemo10();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.run01();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.run02();
            }
        }).start();
        
    }
    
}
