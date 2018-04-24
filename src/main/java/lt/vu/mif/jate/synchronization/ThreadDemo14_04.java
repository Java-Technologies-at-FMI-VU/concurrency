package lt.vu.mif.jate.synchronization;

import lt.vu.mif.jate.synchronization.observer.ObservableSet02;
import lt.vu.mif.jate.synchronization.observer.SetObserver;

public class ThreadDemo14_04 {

    public static void main(String[] args) {
        
        final ObservableSet02<Integer> set = new ObservableSet02<>();
        
        set.addObserver(new SetObserver<Integer>() {
            
            @Override
            public void added(Integer e) {
                System.out.println("Added " + e);
                if (e == 3) {
                    set.removeObserver(this);
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
        
        System.out.println(set.size());
        
    }
    
}
