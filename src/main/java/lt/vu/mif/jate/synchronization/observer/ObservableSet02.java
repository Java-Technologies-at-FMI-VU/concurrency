package lt.vu.mif.jate.synchronization.observer;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet02<E> extends HashSet<E> {

    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();
    
    public void addObserver(SetObserver<E> observer) {
        observers.add(observer);
    }

    public boolean removeObserver(SetObserver<E> observer) {
        return observers.remove(observer);
    }
    
    private void notifyAdded(E element) {
        for (SetObserver<E> observer: observers) {
            observer.added(element);
        }
    }

    private void notifyRemoved(Object element) {
        for (SetObserver<E> observer: observers) {
            observer.removed(element);
        }
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        if (removed) {
            notifyRemoved(o);
        }
        return removed;
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            notifyAdded(e);
        }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object o: c) {
            result |= remove(o);
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e: c) {
            result |= add(e);
        }
        return result;
    }
    
    
    
}
