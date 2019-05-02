package lt.vu.mif.jate.thread;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

class PrimeThread extends Thread {

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

    public PrimeThread(int from, int to, SortedSet<Integer> results) {
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
        }
    }

}

public class ThreadDemo01 {

    public static void main(String[] args) throws InterruptedException {

        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            PrimeThread single = new PrimeThread(1, 300_000, results);
            long start = System.nanoTime();
            single.run();
            System.out.format("01 thread(s): %02d s, %d primes found%n", 
                    TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS), 
                    results.size());

        }

        {

            SortedSet<Integer> results = Collections.synchronizedSortedSet(new TreeSet<>());

            PrimeThread thread01 = new PrimeThread(1, 150_000, results);
            PrimeThread thread02 = new PrimeThread(150_000, 300_000, results);
            
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

            PrimeThread thread01 = new PrimeThread(1, 100_000, results);
            PrimeThread thread02 = new PrimeThread(100_000, 200_000, results);
            PrimeThread thread03 = new PrimeThread(200_000, 300_000, results);
            
            long start = System.nanoTime();
            
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

            PrimeThread thread01 = new PrimeThread(      1,  50_000, results);
            PrimeThread thread02 = new PrimeThread( 50_000, 100_000, results);
            PrimeThread thread03 = new PrimeThread(100_000, 150_000, results);
            PrimeThread thread04 = new PrimeThread(150_000, 200_000, results);
            PrimeThread thread05 = new PrimeThread(200_000, 250_000, results);
            PrimeThread thread06 = new PrimeThread(250_000, 300_000, results);
            
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

            PrimeThread thread01 = new PrimeThread(      1,  30_000, results);
            PrimeThread thread02 = new PrimeThread( 30_000,  60_000, results);
            PrimeThread thread03 = new PrimeThread( 60_000,  90_000, results);
            PrimeThread thread04 = new PrimeThread( 90_000, 120_000, results);
            PrimeThread thread05 = new PrimeThread(120_000, 150_000, results);
            PrimeThread thread06 = new PrimeThread(150_000, 180_000, results);
            PrimeThread thread07 = new PrimeThread(180_000, 210_000, results);
            PrimeThread thread08 = new PrimeThread(210_000, 240_000, results);
            PrimeThread thread09 = new PrimeThread(240_000, 270_000, results);
            PrimeThread thread10 = new PrimeThread(270_000, 300_000, results);
            
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
