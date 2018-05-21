package dicts;

class OrdKVpair<Tk extends Comparable<Tk>, Tv> {

    Tk key;
    Tv value;

    OrdKVpair(Tk k, Tv v) {
        key = k;
        value = v;
    }

    public String toString() {
        return ("<" + key.toString() + ", " + value.toString() + ">");
    }
}
