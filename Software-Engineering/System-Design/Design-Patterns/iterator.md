# Iterator

![UML class diagram](./iterator.png)

You want to be able to **sequentially** access elements of a collection without
exposing it's underlying implementation.

**For example**:

  - you implemented e.g. a hash-set, a list and an array-list
  - you want to retrieve elements from them in the same way without having to
    worry about what goes on under the hood.

## Exmaple

```java
interface Iterator<E> {
    E next();
    E previous();
    E first();
    E last();
    boolean isDone();
}

interface Traversable<E> {
    Iterator<E> getIterator();
}

class ListIterator<E> implements Iterator<E> {

    // implementations of next(), first(),  last(), isDone() ...  

}

class HashSetIterator<E> implements Iterator<E> {

    // implementations of next(), first(),  last(), isDone() ...  

}

class ArrayListIterator<E> implements Iterator<E> {

    // implementations of next(), first(),  last(), isDone() ...  

}

class List<E> implements Traversable<E> {

    public Iterator getIterator() {
        return new ListIterator(this);
    }
}

class HashSet<E> implements Traversable<E> {

    public Iterator getIterator() {
        return new HashSetIterator(this);
    }
}

class ArrayList<E> implements Traversable<E> {

    public Iterator getIterator() {
        return new ArrayListIterator(this);
    }
}
```
