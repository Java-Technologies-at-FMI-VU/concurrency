package lt.vu.mif.jate.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor2 implements Runnable {

    private final int numb;
    private final CountDownLatch latch;

    public Processor2(int numb, CountDownLatch latch) {
        this.numb = numb;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.format("%d. %s starting%n", numb, Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }

        latch.countDown();
        
        System.out.format("%d. %s finishing%n", numb, Thread.currentThread().getName());
    }
}

public class ThreadDemo18 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            executor.submit(new Processor2(i, latch));
        }

        latch.await();

        System.out.println("Completed latch");

        executor.shutdown();
    
    }

}
