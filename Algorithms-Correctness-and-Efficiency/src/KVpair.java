import java.text.MessageFormat;

class KVpair implements Comparable<KVpair> {

    private final Integer key;
    private final String value;

    KVpair(final Integer k, final String v) {
        key = k;
        value = v;
    }

    @Override
    public final int compareTo(final KVpair key) {
        return this.key.compareTo(key.key);
    }

    @Override
    public final String toString() {
        return MessageFormat.format("<{0},{1}>", key, value);
    }
}
