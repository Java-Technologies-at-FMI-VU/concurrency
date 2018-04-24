package lt.vu.mif.jate.concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;

public class ThreadDemo19 {

    @RequiredArgsConstructor
    private static class Task implements Runnable {

        private final CyclicBarrier barrier;

        @Override
        public void run() {
            try {
                
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5000));
                
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
                barrier.await();
                
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5000));

                System.out.println(Thread.currentThread().getName() + " is waiting on barrier again");
                barrier.await();
                
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier again");
                
            } catch (InterruptedException | BrokenBarrierException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    public static void main(String args[]) {

        // creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call
        // await()
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            
            int numberCrossed = 0;
            
            @Override
            public void run() {
                System.out.format("All parties are arrived at barrier (#%d), lets play%n", ++numberCrossed);
            }
        });

        // starting each of thread
        Thread t1 = new Thread(new Task(cb), "Thread 1");
        Thread t2 = new Thread(new Task(cb), "Thread 2");
        Thread t3 = new Thread(new Task(cb), "Thread 3");

        t1.start();
        t2.start();
        t3.start();

    }

}
