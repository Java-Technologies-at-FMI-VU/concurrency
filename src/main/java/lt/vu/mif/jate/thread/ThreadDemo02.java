package lt.vu.mif.jate.thread;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

class PrimeRunner implements Runnable {

    public static boolean isPrime(int n) {
        for (int c = 2; c < n; ++c) {
            if (n % c == 0) {
                return false;
            }
        }
        return true;
    }

    private final int from;
    private final int to;
    private final SortedSet<Integer> results;

    public PrimeRunner(int from, int to, SortedSet<Integer> results) {
        this.from = from;
        this.to = to;
        this.results = results;
    }
    
    
    
    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            if (isPrime(i)) {
                results.add(i);
            }
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
    }

}

public class ThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {

        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            PrimeRunner run = new PrimeRunner(1, 300_000, results);
            long start = System.nanoTime();
            run.run();
            System.out.format("01 thread(s): %02d s, %d primes found%n", 
                    TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS), 
                    results.size());

        }

        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            PrimeRunner run01 = new PrimeRunner(1, 150_000, results);
            PrimeRunner run02 = new PrimeRunner(150_000, 300_000, results);
            
            Thread thread01 = new Thread(run01);
            Thread thread02 = new Thread(run02);
            
            long start = System.nanoTime();
            
            thread01.start();
            thread02.start();
            
            thread01.join();
            thread02.join();
            
            System.out.format("02 thread(s): %02d s, %d primes found%n", 
                    TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS), 
                    results.size());
        }
        
        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            PrimeRunner run01 = new PrimeRunner(1, 100_000, results);
            PrimeRunner run02 = new PrimeRunner(100_000, 200_000, results);
            PrimeRunner run03 = new PrimeRunner(200_000, 300_000, results);
            
            long start = System.nanoTime();
            
            Thread thread01 = new Thread(run01);
            Thread thread02 = new Thread(run02);
            Thread thread03 = new Thread(run03);
            
            thread01.start();
            thread02.start();
            thread03.start();
            
            thread01.join();
            thread02.join();
            thread03.join();
            
            System.out.format("03 thread(s): %02d s, %d primes found%n", 
                    TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS), 
                    results.size());

        }

        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            PrimeRunner run01 = new PrimeRunner(      1,  50_000, results);
            PrimeRunner run02 = new PrimeRunner( 50_000, 100_000, results);
            PrimeRunner run03 = new PrimeRunner(100_000, 150_000, results);
            PrimeRunner run04 = new PrimeRunner(150_000, 200_000, results);
            PrimeRunner run05 = new PrimeRunner(200_000, 250_000, results);
            PrimeRunner run06 = new PrimeRunner(250_000, 300_000, results);
            
            Thread thread01 = new Thread(run01);
            Thread thread02 = new Thread(run02);
            Thread thread03 = new Thread(run03);
            Thread thread04 = new Thread(run04);
            Thread thread05 = new Thread(run05);
            Thread thread06 = new Thread(run06);

            long start = System.nanoTime();
            
            thread01.start();
            thread02.start();
            thread03.start();
            thread04.start();
            thread05.start();
            thread06.start();
            
            thread01.join();
            thread02.join();
            thread03.join();
            thread04.join();
            thread05.join();
            thread06.join();
            
            System.out.format("06 thread(s): %02d s, %d primes found%n", 
                    TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS), 
                    results.size());

        }
        
        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            PrimeRunner run01 = new PrimeRunner(      1,  30_000, results);
            PrimeRunner run02 = new PrimeRunner( 30_000,  60_000, results);
            PrimeRunner run03 = new PrimeRunner( 60_000,  90_000, results);
            PrimeRunner run04 = new PrimeRunner( 90_000, 120_000, results);
            PrimeRunner run05 = new PrimeRunner(120_000, 150_000, results);
            PrimeRunner run06 = new PrimeRunner(150_000, 180_000, results);
            PrimeRunner run07 = new PrimeRunner(180_000, 210_000, results);
            PrimeRunner run08 = new PrimeRunner(210_000, 240_000, results);
            PrimeRunner run09 = new PrimeRunner(240_000, 270_000, results);
            PrimeRunner run10 = new PrimeRunner(270_000, 300_000, results);
            
            Thread thread01 = new Thread(run01);
            Thread thread02 = new Thread(run02);
            Thread thread03 = new Thread(run03);
            Thread thread04 = new Thread(run04);
            Thread thread05 = new Thread(run05);
            Thread thread06 = new Thread(run06);
            Thread thread07 = new Thread(run07);
            Thread thread08 = new Thread(run08);
            Thread thread09 = new Thread(run09);
            Thread thread10 = new Thread(run10);

            long start = System.nanoTime();
            
            thread01.start();
            thread02.start();
            thread03.start();
            thread04.start();
            thread05.start();
            thread06.start();
            thread07.start();
            thread08.start();
            thread09.start();
            thread10.start();
            
            thread01.join();
            thread02.join();
            thread03.join();
            thread04.join();
            thread05.join();
            thread06.join();
            thread07.join();
            thread08.join();
            thread09.join();
            thread10.join();
            
            System.out.format("10 thread(s): %02d s, %d primes found%n", 
                    TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS), 
                    results.size());

        }
        
    }

}
