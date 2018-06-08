import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

class Person extends Thread {

    private String name;
    public static final ReentrantLock lock = new ReentrantLock(true);

    public void talk(int statement) {
        lock.lock();

        try {
            // Speak the next statement, which takes anywhere between 5 and 10
            // individual phrases
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(5, 10); i++) {
                // Speak
                System.out.println(name + " " + statement);
                // Pause for breath
                this.wait(200);
            }
        } finally {
            lock.unlock();
        }
    }

    // Wrap Thread.sleep
    public void wait(int time) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void run() {
        // Yes I'm chatty...
        for (int statement = 0;; statement++) {
            talk(statement);
            // .. but I need time to think of my next whitty rejoinder
            this.wait(1000);
        }
    }

    public Person(String name) {
        this.name = name;
    }
}

// *****************************************************************************

public class Conversation {

    public static void main(String args[]) {
        Person cornelius = new Person("Cornelius");
        cornelius.start();
        Person maureen = new Person("Maureen");
        maureen.start();
        Person mort = new Person("Mort");
        mort.start();
        Person cassio = new Person("Cassiopeia");
        cassio.start();
    }
}
