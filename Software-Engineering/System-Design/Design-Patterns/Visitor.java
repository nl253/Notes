import java.util.Arrays;
import java.util.Collection;
import java.util.List;

interface IVisitable<E> {

    E accept(IVisitor<E> visitor);
}


@FunctionalInterface
interface IVisitor<E> {

    E visit(IVisitable<E> visitable);
}

public final class Visitor {

    private Visitor() {}

    public static void main(final String... args) {
        final IVisitable<Integer> tree = new BinaryTree(1, 2, 3, 4, 5);
        System.out.println("Result is: " + tree.accept(new Adder()));
    }

    private static class Adder implements IVisitor<Integer> {

        @Override
        public final Integer visit(final IVisitable<Integer> visitable) {
            if (visitable == null) return 0;
            int result = 0;
            final BinaryTree<Integer> t = (BinaryTree<Integer>) visitable;
            if (t.data != null) result += t.data;
            if (t.left != null) result += t.left.accept(this);
            if (t.right != null) result += t.right.accept(this);
            return result;
        }
    }
}

@SuppressWarnings("PublicField")
class BinaryTree<E extends Comparable<E>> implements IVisitable<E> {

    public BinaryTree<E> left, right;
    public E data;

    @SafeVarargs
    public BinaryTree(final E... vals) {
        this(Arrays.asList(vals));
    }

    public BinaryTree(final List<E> vals) {
        insertAll(vals);
    }

    private void insertAll(final Collection<E> vals) {
        for (final E val : vals) insert(val);
    }

    private void insert(final E val) {
        if (data == null) data = val;
        else if (val.compareTo(data) < 0) {
            if (left == null) left = new BinaryTree<>();
            left.insert(val);
        } else {
            if (right == null) right = new BinaryTree<>();
            right.insert(val);
        }
    }

    @Override
    public E accept(final IVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
