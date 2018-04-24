package lt.vu.mif.jate.synchronization;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lt.vu.mif.jate.synchronization.observer.ObservableSet02;
import lt.vu.mif.jate.synchronization.observer.SetObserver;

public class ThreadDemo14_05 {

    public static void main(String[] args) {
        
        final ObservableSet02<Integer> set = new ObservableSet02<>();
        
        set.addObserver(new SetObserver<Integer>() {
            
            @Override
            public void added(Integer e) {
                System.out.println("Added " + e);
                if (e == 3) {
                    
                    SetObserver<Integer> observer = this;
                    
                    try {
                        ExecutorService ex = Executors.newSingleThreadExecutor();
                        ex.submit(new Runnable() {

                            @Override
                            public void run() {
                                set.removeObserver(observer);
                            }

                        }).get();
                        ex.shutdown();
                        
                    } catch (ExecutionException | InterruptedException ex) { }
                }
            }

            @Override
            public void removed(Object e) {
                System.out.println("Removed " + e);
            }
        });
        
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

        set.remove(1);
        
    }
    
}
