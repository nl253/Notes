package stacks;

class Node<T> {

    public T head;
    public Node<T> tail;

    Node(final T head, final Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public final String toString() {
        return (tail == null) ? head.toString() : (head.toString() + tail);
    }
}

public class LinkedStack<T> {

    private Node<T> stack;

    public final T pop() throws EmptyLinkedStack {
        if (stack == null) throw new EmptyLinkedStack();

        final T val = stack.head;
        stack = stack.tail;
        return val;
    }

    public final void push(final T val) {
        stack = new Node<>(val, stack);
    }

    public final boolean isEmpty() {
        return stack == null;
    }
}
