class EmptyStack extends Exception {}

class FullStack extends Exception {}

public class Stack {

    private String[] st;

    // next is the index of the first empty slot in the stack
    private int next;

    public String pop() throws EmptyStack {
        if (next == 0) throw new EmptyStack();

        next--;
        String v = st[next];
        st[next] = null;
        return v;
    }

    public void push(String v) throws FullStack {
        if (next == st.length) throw new FullStack();

        st[next] = v;
        next++;
    }

    public boolean isEmpty() {
        return (next == 0);
    }

    public Stack(int size) {
        st = new String[size];
    }

    public void print() {
        System.out.println("Stack:");
        System.out.println("|   ...");
        System.out.println("|---------------");

        for (int i = next; i > 0; i--) {
            System.out.println("| " + st[i - 1]);
            System.out.println("|---------------");
        }
    }

    public static void main(String[] args) {
        Stack s = new Stack(5);

        try {
            s.print();
            s.push("A");
            s.push("B");
            s.push("C");
            s.print();
            System.out.println("popping: " + s.pop());
            s.print();
            s.push("D");
            s.print();
            System.out.println("popping: " + s.pop());
            s.print();
            System.out.println("popping: " + s.pop());
            s.print();
            System.out.println("popping: " + s.pop());
            s.print();
            System.out.println("popping: " + s.pop());
            s.print();
        } catch (EmptyStack e) {
            System.out.println("popping empty stack");
        } catch (FullStack e) {
            System.out.println("pushing full stack");
        }
    }
}
