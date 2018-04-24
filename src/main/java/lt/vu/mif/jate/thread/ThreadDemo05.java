package lt.vu.mif.jate.thread;

import java.util.concurrent.TimeUnit;

public class ThreadDemo05 {

    public static void main(String[] args) throws InterruptedException {

        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"};
        
        for (int i = 0; i < importantInfo.length; i++) {
            
            //Pause for 1 second 
            //Thread.sleep(1000);
            TimeUnit.SECONDS.sleep(1);
            
            //Print a message  
            System.out.println(importantInfo[i]);
            
        }

    }

}
