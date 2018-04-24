package lt.vu.mif.jate.concurrency;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Sum extends RecursiveTask<Long> { 
    
    private static final int THRESHOLD = 100;
    
    private final int[] data;
    private final int low;
    private final int high;
    
    @Override
    protected Long compute() { 
        if(high - low <= THRESHOLD) { 
            
            long sum = 0; 
            for(int i = low; i < high; ++i)  sum += data[i]; 
            return sum; 
            
         } else {
            
            int mid = low + (high - low) / 2; 
            Sum left  = new Sum(data, low, mid); 
            Sum right = new Sum(data, mid, high); 
            left.fork(); 
            long rightAns = right.compute(); 
            long leftAns  = left.join(); 
            return leftAns + rightAns; 
            
         } 
     } 
} 


public class ThreadDemo20 {
    
    private static final int LEN = 100_000_000;
    private static final Random RAND = new Random();
    
    public static void main(String[] args) {
        
        int[] data = new int[LEN];
        for (int i = 0; i < LEN; i++) {
            data[i] = RAND.nextInt();
        }
        
        Sum sum = new Sum(data, 0, LEN);
        
        long start = System.nanoTime();
        long res = sum.compute();
        long end = System.nanoTime();
        
        System.out.format("simple   : %d in %d ms%n", res, TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS));
        
        start = System.nanoTime();
        res = new ForkJoinPool().invoke(sum);
        end = System.nanoTime();
        
        System.out.format("join/fork: %d in %d ms%n", res, TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS));
        
    }
    
}
