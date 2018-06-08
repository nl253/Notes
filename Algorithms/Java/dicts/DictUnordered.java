package dicts;

import java.util.List;

public class DictUnordered<K, V> {

    private List<KVpair<K, V>> pairs;

    public final V lookup(final K key) throws NotFound {

        for (int i = pairs.size() - 1; i >= 0; i--)
            if (key.equals(pairs.get(i).key))
                return pairs.get(i).value;

        throw new NotFound();
    }

    public final void add(final K key, final V val) {
        pairs.add(new KVpair<>(key, val));
    }
}

