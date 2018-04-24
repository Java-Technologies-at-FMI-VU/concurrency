package lt.vu.mif.jate.synchronization.observer;

public interface SetObserver<E> {

    void added(E e);
    void removed(Object e);
    
}
