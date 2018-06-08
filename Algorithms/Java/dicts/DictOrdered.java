package dicts;

import java.util.ArrayList;

class DictOrdered<K extends Comparable<K>, V> {

    // Assume that the dictionary is in ascending order of keys.
    private final ArrayList<OrdKVpair<K, V>> pairs = new ArrayList<>();

        // Return the index of key in the ArrayList pairs between lower and upper
    // (including the elements at lower and upper). Return -1 if key is not in pairs.
    private int getIndex(final K key, final int lower, final int upper) {
        // If there is nothing between lower and upper, of they are not in the array
        // bounds, then return -1.
        if ((upper < lower) || (lower < 0) || (upper >= pairs.size())) return -1;

        //Possible signed arithmetic overflow:
        //int middle = (lower + upper) / 2;
        final int middle = ((upper - lower) / 2) + lower;
        final int cmp = pairs.get(middle).key.compareTo(key);

        if (cmp == 0) return middle;
        else if (cmp < 0) return getIndex(key, middle + 1, upper);
        else return getIndex(key, lower, middle - 1);
    }

    private int getIndex2(final K key, int lower, int upper) {
        while (upper >= lower) {
            final int middle = ((upper - lower) / 2) + lower;
            final int cmp = pairs.get(middle).key.compareTo(key);

            if (cmp == 0) return middle;
            else if (cmp < 0) lower = middle + 1;
            else upper = middle - 1;
        }

        return -1;
    }

    // Return the index of the first the ArrayList pairs that is bigger than k and
    // between lower and upper (including the elements at lower and upper).
    // Assume that there is such an element and that lower <= upper, and that
    // k is not in the ArrayList.
    private int getIndexForAdd(final K k, final int lower, final int upper) {
        if (lower == upper) return lower;

        final int middle = ((upper - lower) / 2) + lower;
        final int comp = k.compareTo(pairs.get(middle).key);

        if (comp == 0) {
            assert false;
            return 0;
        } else if (comp < 0) return getIndexForAdd(k, lower, middle);
        else return getIndexForAdd(k, middle + 1, upper);
    }

    public final V lookup(final K k) throws NotFound {
        final int idx = getIndex(k, 0, pairs.size() - 1);

        if (idx == -1) throw new NotFound();
        else return pairs.get(idx).value;
    }

    public final void add(final K key, final V val) {
        final OrdKVpair<K, V> newPair = new OrdKVpair<>(key, val);
        final int idx = getIndex(key, 0, pairs.size() - 1);

        if (idx == -1)
            if (pairs.isEmpty() || (key.compareTo(pairs.get(pairs.size() - 1).key) > 0))
                pairs.add(newPair);
            else
                // NB, this add is NOT constant amortised time, because it has to copy all
                // of the elements in the ArrayList after the spot for key on each add.
                pairs.add(getIndexForAdd(key, 0, pairs.size() - 1), newPair);
        else pairs.set(idx, newPair);
    }

    @Override
    public final String toString() {
        return pairs.toString();
    }
}
