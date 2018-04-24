package lt.vu.mif.jate.thread;

public class ThreadDemo04 {

    private static void printInfo(final Thread t) {
        System.out.format("*********%n");
        System.out.format("Id = %d%n", t.getId());
        System.out.format("Name = %s%n", t.getName());
        System.out.format("Priority = %d%n", t.getPriority());
        System.out.format("State = %s%n", t.getState().name());
        System.out.format("Group name = %s%n", t.getThreadGroup().getName());
        System.out.format("Alive = %s%n", t.isAlive());
        System.out.format("Daemon = %s%n", t.isDaemon());
        System.out.format("Interrupted = %s%n", t.isInterrupted());
        
    }
    
    public static void main(String[] args) {
        
        printInfo(Thread.currentThread());
        
        Thread t = new Thread("Some thread") {

            @Override
            public void run() {
                printInfo(Thread.currentThread());
            }
            
        };
        
        printInfo(t);
        
        t.setDaemon(true);
        
        printInfo(t);
        
        t.start();
        
        t.interrupt();
        
        printInfo(t);
        
    }
    
}
