package lt.vu.mif.jate.synchronization;

import java.util.concurrent.TimeUnit;

public class ThreadDemo07 {

    synchronized void doWait() throws InterruptedException {
        System.out.format("%s: waiting%n", Thread.currentThread().getName());
        wait();
        System.out.format("%s: released%n", Thread.currentThread().getName());
    }
    
    synchronized void doNotify() {
        System.out.format("%s: notify%n", Thread.currentThread().getName());
        notify();
    }

    synchronized void doNotifyAll() {
        System.out.format("%s: notify all%n", Thread.currentThread().getName());
        notifyAll();
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        ThreadDemo07 d = new ThreadDemo07();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        d.doWait();
                    } catch (InterruptedException ex) { }
                }
            }).start();
        }
        
        TimeUnit.SECONDS.sleep(3);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        TimeUnit.SECONDS.sleep(1);
                        d.doNotify();
                    }                  
                } catch (InterruptedException ex) { }
//                d.doNotifyAll();
            }
        }).start();
        
    }
    
}
