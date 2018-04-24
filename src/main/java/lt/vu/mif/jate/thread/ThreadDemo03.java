package lt.vu.mif.jate.thread;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class ThreadDemo03 {

    public static void main(String[] args) throws InterruptedException {

        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            ThreadGroup tg = new ThreadGroup("Prime calculators");
            
            Thread thread01 = new Thread(tg, new PrimeRunner(      1,  50_000, results));
            Thread thread02 = new Thread(tg, new PrimeRunner( 50_000, 100_000, results));
            Thread thread03 = new Thread(tg, new PrimeRunner(100_000, 150_000, results));
            Thread thread04 = new Thread(tg, new PrimeRunner(150_000, 200_000, results));
            Thread thread05 = new Thread(tg, new PrimeRunner(200_000, 250_000, results));
            Thread thread06 = new Thread(tg, new PrimeRunner(250_000, 300_000, results));

            long start = System.nanoTime();

            thread01.start();
            thread02.start();
            thread03.start();
            thread04.start();
            thread05.start();
            thread06.start();

            System.out.format("Active count %d%n", tg.activeCount());
            
            tg.interrupt();
            
            thread01.join();
            thread02.join();
            thread03.join();
            thread04.join();
            thread05.join();
            thread06.join();
            
            System.out.format("06 thread(s): %02d ms, %d primes found%n", 
                    TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS), 
                    results.size());

        }
        
    }

}
