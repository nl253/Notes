package lists;

abstract class LinkedList<T> {

    // return the length of the list
    abstract int length();

    // return the element at the idx spot in the list (from 0). Raise EndOfList
    // if the list is not long enough.
    abstract public T get(int idx) throws EndOfList;
}

class Empty<T> extends LinkedList<T> {

    @Override
    final int length() {
        return 0;
    }

    @Override
    public final T get(final int idx) throws EndOfList {
        throw new EndOfList();
    }

    @Override
    public final String toString() {
        return "";
    }
}

class Node<T> extends LinkedList<T> {

    private final T head;
    private final LinkedList<T> tail;

    Node(final T h, final LinkedList<T> t) {
        head = h;
        tail = t;
    }

    // The length of the list is just one more than the length of the tail
    @Override
    public final int length() {
        return 1 + tail.length();
    }

    // If we aren't looking for this node, we need to look in the tail of the
    // list, but only for 1 fewer steps.
    @Override
    public final T get(final int idx) throws EndOfList {
        return (idx == 0) ? head : tail.get(idx - 1);
    }

    // Convert the tail to a string, and then add the head string on to the front
    @Override
    public final String toString() {
        return head.toString() + tail;
    }
}
