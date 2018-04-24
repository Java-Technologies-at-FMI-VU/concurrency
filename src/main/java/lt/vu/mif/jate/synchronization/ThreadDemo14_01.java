package lt.vu.mif.jate.synchronization;

import lt.vu.mif.jate.synchronization.observer.ObservableSet;
import lt.vu.mif.jate.synchronization.observer.SetObserver;

public class ThreadDemo14_01 {

    public static void main(String[] args) {
        
        ObservableSet<Integer> set = new ObservableSet<>();
        
        set.addObserver(new SetObserver<Integer>() {
            
            @Override
            public void added(Integer e) {
                System.out.println("Added " + e);
            }

            @Override
            public void removed(Object e) {
                System.out.println("Removed " + e);
            }
        });
        
        set.add(0);
        set.add(1);
        set.add(2);

        set.remove(1);
        
    }
    
}
