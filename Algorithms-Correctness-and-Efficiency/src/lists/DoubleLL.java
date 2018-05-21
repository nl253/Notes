package lists;

class DoubleLLNode<T> {

    public T head;
    public DoubleLLNode<T> tail, pred;

    // Just put the head value in the node, let the queue manage the tail and pred
    DoubleLLNode(final T h) {
        head = h;
        // tail and pred are null by default. Assigning for emphasis.
        tail = null;
        pred = null;
    }
}

final class DoubleLL {

    private DoubleLL() {}
    // There isn't much you can do with a double linked list on its own. Usually,
    // it's used to implement some particular data structure. See
    // LinkedQueue.java, for example.

    // Copied from ProcLinkedList.java, since the double list includes the same
    // structure as the single, but with more
    public static <T> int length(final DoubleLLNode<T> node) {
        int len = 0;
        DoubleLLNode<T> point = node;

        while (point != null) {
            len++;
            point = point.tail;
        }

        return len;
    }

    // Copied from ProcLinkedList.java
    public static <T> T get(final DoubleLLNode<T> node, int idx) throws EndOfList {
        DoubleLLNode<T> point = node;

        while ((idx != 0) && (point != null)) {
            idx--;
            point = point.tail;
        }

        if (point != null) return point.head;
        else throw new EndOfList();
    }

    // Given a DoubleLLNode node that might be in the middle of the list, return the first
    // node.
    public static <T> DoubleLLNode<T> findFirst(final DoubleLLNode<T> node) {
        DoubleLLNode<T> focus = node;

        if (focus == null) return null;
        else while (focus.pred != null) focus = focus.pred;

        return focus;
    }
}
