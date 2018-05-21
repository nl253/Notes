package queues;

class Node<T> {

    public T head;
    public Node<T> tail, pred;

    // Just put the head value in the node, let the queue manage the tail and pred
    Node(final T h) {
        head = h;
        // tail and pred are null by default. Assigning for emphasis.
        tail = null;
        pred = null;
    }
}

public class LinkedQueue<T> {

    // front points to the front of the queue and back the back.
    // In an empty queue both are null.
    // In a single element queue, both point to the same node.
    private Node<T> front, back;

    public void enqueue(final T v) {
        final Node<T> tmp = new Node<T>(v);

        if (front == null) {
            // empty queue to single element queue, tail and pred remain null
            front = tmp;
            back = tmp;
        } else {
            tmp.pred = back;
            back.tail = tmp;
            back = back.tail;
        }
    }

    public T dequeue() throws EmptyQueue {
        if (front == null) throw new EmptyQueue();

        final T result = front.head;
        front = front.tail;

        // LinkedQueue just became empty, so it used to be single element with back ==
        // front. Now we need empty with back == null and front == null
        if (front == null) back = null;
        else front.pred = null;

        return result;
    }
}
