package lt.vu.mif.jate.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDemo18_01 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ExecutorService ex = Executors.newFixedThreadPool(3);
        
        Collection<Callable<Integer>> tasks = new ArrayList<>();
        
        tasks.add(new PrimeCounter(1, 100_000));
        tasks.add(new PrimeCounter(100_000, 200_000));
        tasks.add(new PrimeCounter(200_000, 300_000));

        Integer result = 0;
        
        long started = System.nanoTime();
        for (Future<Integer> r: ex.invokeAll(tasks)) {
            result += r.get();
        }
        long ended = System.nanoTime();
                
        System.out.format("Result %d in %d ns%n", result, ended - started);
        
        ex.shutdown();
        
    }
    
}
