class EndOfList extends Exception {};

class Node<T> {

    public T head;
    public Node<T> tail;

    public Node(T h, Node<T> t) {
        head = h;
        tail = t;
    }

    @Override
    public String toString() {
        return (tail == null) ? head.toString() : head.toString() + tail.toString();
    }
}

class LinkedList {

    // The length of the list is just one more than the length of the tail
    public static <T> int length(Node<T> n) {
        return (n == null) ? 0 : 1 + length(n.tail);
    }

    // If we aren't looking for this node, we need to look in the tail of the
    // list, but only for 1 fewer steps.
    public static <T> T get(Node<T> n, int idx) throws EndOfList {
        if (idx == 0 && n != null) return n.head;
        else if (n == null) throw new EndOfList();
        else return get(n.tail, idx - 1);
    }
}

// For these simple recursive methods, we can use equivalent loops, but this
// only works easily when there is only a single recursive call.
class LinkedList2 {

    public static <T> int length(Node<T> n) {

        int l = 0;

        Node<T> point = n;

        while (point != null) {
            l++;
            point = point.tail;
        }

        return l;
    }

    public static <T> T get(Node<T> n, int idx) throws EndOfList {

        Node<T> point = n;

        while (idx != 0 && point != null) {
            idx--;
            point = point.tail;
        }

        if (point != null) return point.head;
        else throw new EndOfList();
    }
}
