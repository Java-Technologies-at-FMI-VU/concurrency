package lt.vu.mif.jate.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PrimeCounter implements Callable<Integer> {

    private final int from;
    private final int to;
    
    public static boolean isPrime(int n) {
        for (int c = 2; c < n; ++c) {
            if (n % c == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer call() {
        int count = 0;
        for (int i = from; i <= to; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
}

public class ThreadDemo17 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        FutureTask<Integer> task = new FutureTask<>(new PrimeCounter(1, 300_000));
        
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(task);
        
        System.out.print("Doing something else..");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("done");
        
        System.out.println("Using result: " + task.get());
        
        ex.shutdown();
        
    }
    
}
