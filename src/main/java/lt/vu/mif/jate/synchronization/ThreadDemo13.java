package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo13 {

    static class Runner implements Runnable {

        ReentrantLock lock = new ReentrantLock();
        
        @Override
        public void run() {
            boolean acquired = false;
            while (!acquired) {
                try {
                    print("waiting for lock");
                    acquired = lock.tryLock(500, TimeUnit.MILLISECONDS);
                    if (acquired) {
                        print("got lock!");
                        Thread.sleep(1000);
                    } else {
                        print("timeout...");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException ex) { 
                } finally {
                    if (acquired) {
                        lock.unlock();
                        print("released lock");
                    }
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        Runner r = new Runner();
        
        for (int i = 0; i < 6; i++) {
            new Thread(r).start();
        }
        
    }
    
    static void print(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
        
}
