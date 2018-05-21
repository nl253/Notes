public final class Sorting {

    private Sorting() {}

    public static <T extends Comparable<T>> void insertionSort(final T[] array) {
        // index is the last index of the sorted array
        for (int index = 0; index < (array.length - 1); index++) {
            final T item = array[index + 1];
            int i = index;

            // Loop until i falls off the front end, or we find array key less than or
            // equal to item.
            while ((i >= 0) && (item.compareTo(array[i]) < 0)) {
                array[i + 1] = array[i];
                i--;
            }

            array[i + 1] = item;
        }
    }

    // Sort the part of the array a that is between first and last (inclusive).
    // Assume that first <= last
    public static <T extends Comparable<T>> void quickSort(final T[] a, final int first, final int last) { }
}
