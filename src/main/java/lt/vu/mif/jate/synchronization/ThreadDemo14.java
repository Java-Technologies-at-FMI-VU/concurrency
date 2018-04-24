package lt.vu.mif.jate.synchronization;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class ProducerConsumer<E> implements Runnable {
    
    @Getter
    protected final BlockingQueue<E> queue;
    
    static void print(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + (msg == null ? "null" : msg));
    }
    
}

class Consumer extends ProducerConsumer<String> {

    public Consumer() {
        super(new LinkedBlockingQueue<>());
    }

    @Override
    public void run() {
        String msg = null;
        do {
            try {
                
                msg = queue.take();
                print(msg);
                
            } catch (InterruptedException ex) { }
        } while (!msg.equals("exit"));
    }

}
   
class Producer extends ProducerConsumer<String> {

    public Producer(BlockingQueue queue) {
        super(queue);
    }

    @Override
    public void run() {
        try {

            queue.add("Labas");
            TimeUnit.SECONDS.sleep(1);
            
            queue.add("pavasari");
            TimeUnit.SECONDS.sleep(1);
            
            queue.add("!");
            TimeUnit.SECONDS.sleep(1);

            queue.add("exit");
            
        } catch (InterruptedException ex) { }
    }

}


public class ThreadDemo14 {    
    
    public static void main(String[] args) {
        
        Consumer consumer = new Consumer();
        Producer producer = new Producer(consumer.queue);
        
        Thread consumerThread = new Thread(consumer, "Consumer thread");
        Thread producerThread = new Thread(producer, "Producer thread");

        //consumerThread.setDaemon(true);
        
        consumerThread.start();
        producerThread.start();                
        
    }
    
}
