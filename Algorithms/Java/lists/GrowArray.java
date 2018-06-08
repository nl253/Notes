package lists;

// A class for extendable arrays, as in ArrayList or Vector.
public class GrowArray {

    private int size;
    private String[] contents;

    public GrowArray(final int initialSize) {
        contents = new String[initialSize];
    }

    public final int size() {
        return size;
    }

    public String get(final int i) {
        if (i >= size) throw new ArrayIndexOutOfBoundsException();
        return contents[i];
    }

    public void add(final String s) {

        if (size < contents.length) {
            contents[size] = s;
            size++;
            return;
        }

        final String[] contents2 = new String[(size << 1)];
        size <<= 1;
        System.arraycopy(contents, 0, contents2, 0, contents.length);
        contents2[size] = s;
        size++;
        contents = contents2;
    }
}
