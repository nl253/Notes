class Example extends Thread {

    private Object lock = new Object();
    private String name;

    void A() {
        synchronized (lock) {
            for (int i = 0; i < 3; i++) {
                // Print a message
                System.out.println(name + " doing A.");
                this.sleep();
            }
        }
    }

    void B() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                // Print a message
                System.out.println(name + " doing B.");
                this.sleep();
            }
        }
    }


    public void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void run() {
        // Do A
        this.A();
        // Sleep
        this.sleep();
        // Do B
        this.B();
    }

    public Example(String name, Object lock) {
        this.name = name;
        this.lock = lock;
    }
}

public class MultiLock {

    public static void main(String args[]) {
        Object sharedLock = new Object();
        Example ex1 = new Example("Thread 1", sharedLock);
        Example ex2 = new Example("Thread 2", sharedLock);
        ex1.start();
        ex2.start();
    }

}
