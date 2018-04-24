package lt.vu.mif.jate.thread;

class InterruptDemo extends Thread {

    @Override
    public void run() {
        try {
            
            while (true) {
                System.out.println("01: Running...");
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException ex) {
            // Proper partern is to re-interrupt the thread!
            //interrupt();
            System.out.println("01: Interrupted!");
        }

        if (isInterrupted()) {
            System.out.println("01: Still interrupted...");
        } else {
            System.out.println("01: Not really interrupted...");
        }
        
        int i = 0;
        while (i >= 0) {
            
            if ((i++ % 200_000_000) == 0) {
                System.out.println("02: Running again...");
                if (isInterrupted()) {
                    System.out.println("02: Interrupted!");
                    // This line would crear the interrupt!
                    //interrupted();
                    break;
                }
            }
            
        }
        
        if (isInterrupted()) {
            System.out.println("02: Still interrupted...");
        } else {
            System.out.println("02: Not really interrupted...");
        }

    }
    
}

public class ThreadDemo06 {

    public static void main(String[] args) throws InterruptedException {

        InterruptDemo t = new InterruptDemo();
        t.start();

        Thread.sleep(3000);
        t.interrupt();

        Thread.sleep(1000);
        t.interrupt();
        
    }
    
}
