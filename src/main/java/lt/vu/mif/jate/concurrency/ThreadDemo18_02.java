package lt.vu.mif.jate.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;

@Getter
class Timer <V> {
    
    private Collection<V> results;
    private long elapsed = 0;
    
    public void execute(Executor ex, Collection<Callable<V>> tasks) throws InterruptedException {
        
        CountDownLatch ready = new CountDownLatch(tasks.size());
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(tasks.size());
        
        results = new ArrayList<>();
        
        for (Callable<V> task: tasks) {
            ex.execute(new Runnable() {
                @Override
                public void run() {
                    ready.countDown();
                    try {
                        
                        start.await();
                        results.add(task.call());
                        
                    } catch (Exception ex) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown();
                    }

                }
            });
        }
        
        ready.await();
        long started = System.nanoTime();
        start.countDown();
        done.await();
        elapsed = System.nanoTime() - started;
        
    }
    
}

public class ThreadDemo18_02 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ExecutorService ex = Executors.newFixedThreadPool(3);
        
        Collection<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(new PrimeCounter(1, 100_000));
        tasks.add(new PrimeCounter(100_000, 200_000));
        tasks.add(new PrimeCounter(200_000, 300_000));

        Timer<Integer> timer = new Timer<>();
        timer.execute(ex, tasks);
        
        int result = 0;
        for (Integer i: timer.getResults()) {
            result += i;
        }
                
        System.out.format("Result %d in %d ns%n", result, timer.getElapsed());
        
        ex.shutdown();
        
    }
    
}
