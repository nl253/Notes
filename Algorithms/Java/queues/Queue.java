package queues;

public class Queue {

    private final String[] q;
    // head indexes the first element in the queue, unless size is 0, in which
    // case the queue is empty.
    private int head, size;

    Queue(final int s) {
        head = size = 0;
        q = new String[s];
    }

    public final void enqueue(final String s) throws FullQueue {
        if (q.length == size) throw new FullQueue();
        q[(head + size) % q.length] = s;
        size++;
    }

    public final String dequeue() throws queues.EmptyQueue {
        if (size == 0) throw new queues.EmptyQueue();

        final String tmp = q[head];
        q[head] = null;
        head = (head + 1) % q.length;
        size--;
        return tmp;
    }

    public final boolean isEmpty() {
        return size == 0;
    }
}
