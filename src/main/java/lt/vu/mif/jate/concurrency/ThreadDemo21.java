package lt.vu.mif.jate.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadDemo21 {

    static Exchanger<Integer> exchanger = new Exchanger<>();

    static class Part01 implements Runnable {

        @Override
        public void run() {
            Integer i = 0;
            try {
                
                while (i < 10) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Part01 have "+ i);
                    i = exchanger.exchange(i, 3, TimeUnit.SECONDS);
                    i++;
                }
                
            } catch (InterruptedException | TimeoutException ex) {
                System.out.println("Part01 throwed "+ ex);
            }
     }
   }

   static class Part02 implements Runnable {

        @Override
        public void run() {
            Integer j = 1;
            try {
                
                while (j < 10) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Part02 have "+ j);
                    j = exchanger.exchange(j, 3, TimeUnit.SECONDS);
                }
                
                exchanger.exchange(10, 1, TimeUnit.SECONDS);
                
            } catch (InterruptedException | TimeoutException ex) {
                System.out.println("Part02 throwed "+ ex);
            }
            
     }
   }

    public static void main(String[] args) {
        new Thread(new Part01()).start();
        new Thread(new Part02()).start();
    }
    
}
