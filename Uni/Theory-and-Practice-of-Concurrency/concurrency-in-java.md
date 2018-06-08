# Java and Concurrency

**NOTE** take a look at java 8 and java 9 changes to concurrency (java
10 did't focus on it at all).

## Syntax

### Try-Catch-Finally

The `finally` clasuse gets executed always\*.

\* More specificially it is run when the `try` clause finishes. The
consequence of this is that if the JVM exits in the `try` block, it
would not be able to run the code in the `finally` block.

### Synchronised

Locks in `synchronized` methods:

When a thread invokes a synchronized method, it automatically acquires
the "intrinsic lock" for that method's object and releases it when the
method returns.

Synchronization is built around an internal entity known as the
intrinsic lock or monitor lock.

**NOTE** The API specification often refers to this entity simply as a
"monitor".

Synchronized statements are useful for improving concurrency with
fine-grained synchronization.

**NOTE** Invoking other objects' methods from synchronized code can
create problems.

#### Method vs Block

**Scenario 1**:

-   Shared object.
-   Single method.
-   We want to have multiple threads.

**Scenario 2**:

-   Multiple instances of a class.
-   Use static `Object` lock.

**Scenario 3**:

Different methods in 2 objects synchronise on *the same* lock (this means they share
the `lock`). This means both cannot execute at the same time. This is despite
them being separate objects.

``` java
Lock l = new Lock();
Example e1 = new Example(null, l);
Example e2 = new Example(null, l);
e1.A();
e2.A();
```

Inside the `Example` class:

``` java
public class Example {

    private Lock lock;

    public Example(Object o, Lock l) {
        this.lock = l;
    }

    public void A() {
        synchronized(this.lock) {
            // code
        }
    }
}
```

#### Synchronised on `this` vs `synchronized` methods

- Can lead to interleaving since `this` is not a shared piece of info.
- It might be better to use a `static` `Lock`.

These are equivalent:

``` java
method() {
    synchronized() {
        // code
    }
}
```

But if you have something else that also attempts to synchronize on `this` it
will block as well (which might not be what you want).

``` java
synchronized method() {
    // code
}
```

#### Static Synchronised

The thread acquires the intrinsic lock for the Class object associated
with the class. Thus access to class's static fields is controlled by a
lock that's distinct from the lock for any instance of the class.

## Shared Memory

-   multiple concurrent "threads"
-   access to shared state is implicit communication
-   use the `Thread` class from the Java stdlib or use the `Runnable`
    interface
-   use `myThread.start()` to kick-start it
-   use `myThread.join()` to block until completion
-   use `myThread.run()` to run sequentially
-   java has a special keyword `synchronised` which can be added to any
    method signature e.g.: `public synchronised Tea vend()`
-   `synchronised` does not avoid resource starvation (some threads
    might not get access to the resource)
-   a more fair way of doing it is having a globally accessible `Lock`
-   the basic methods in the Lock interface are
    `lock.tryLock(time, timeUnit)`, `lock.lock()` and `lock.unlock()`

``` java
lock.lock();

synchronized (lock) {
    try {
        // critical section
    } finally {
        // once acquired by `p1` the lock needs to be released by `p1`
        // and not any arbitrary process
        lock.unlock();
        // trying to unlock a lock without having acquired it before  will throw an exception
    }
}
```

The lock is implemented with a queue which ensures "fairness".

## Synchronisation

### Volatile

Essentially, volatile is used to indicate that a **variable's value will
be modified by different threads**.

Declaring a volatile Java variable means:

-   The value of this variable will never be cached thread-locally: all
    reads and writes will go straight to "main memory".
-   Access to the variable acts as though it is enclosed in a
    `synchronized` block, synchronized on itself.

Threads can have their own copy of data for variables, and the thread
copy can be different from the "main" memory.

Using volatile variables reduces the risk of memory consistency errors,
because any write to a volatile variable establishes a happens-before
relationship with subsequent reads of that same variable. This means
that changes to a volatile variable are always visible to other threads.
What's more, it also means that when a thread reads a volatile variable,
it sees not just the latest change to the volatile, but also the side
effects of the code that led up the change.

#### Use Case: A "simple flag" accessed by multiple threads

-   you write a variable, such as a flag, in one thread
-   you check that variable in another thread

See [synchronization volatile typical
use](https://www.javamex.com/tutorials/synchronization_volatile_typical_use.shtml).

### Synchronised vs Volatile

Volatile is a field modifier, while synchronized modifies code blocks
and methods.

Effectively, a variable declared volatile must have its data
synchronized across all threads, so that whenever you access or update
the variable in any thread, all other threads immediately see the same
value. Generally, volatile variables have a higher access and update
overhead than "plain" variables.

Typically, threads are allowed to have their own copy of data, which is
for better efficiency.

### Atomic

happens all at once. An atomic action cannot stop in the middle: it
either happens completely, or it doesn't happen at all. No side effects
of an atomic action are visible until the action is complete.

Declaring an atomic variable guarantees that operations made on the
variable occur in an atomic fashion, i.e., that all of the substeps of
the operation are completed within the thread they are executed and are
not interrupted by other threads.

Even very simple expressions can define complex actions that can
decompose into other actions.

**NOTE**: an increment expression, such as `i++`, does not describe an
atomic action.

Atomic actions:

-   Reads and writes are atomic for reference variables and for most
    primitive variables (all types except long and double).
-   Reads and writes are atomic for all variables declared `volatile`
    (including `long` and `double` variables).

### Atomic vs Volatile

The effect of the volatile keyword is approximately that each individual
read or write operation on that variable is atomic.

Notably, however, an operation that requires more than one read/write --
such as `i++`, which is equivalent to `i = i + 1`, which does one read
and one write -- is not atomic, since another thread may write to i
between the read and the write.

The Atomic classes, like AtomicInteger and AtomicReference, provide a
wider variety of operations atomically, specifically including increment
for AtomicInteger.

## Definitions

Starvation

:   a thread is unable to gain regular access to shared resources and is
    unable to make progress.

Livelock
:   two or more threads react to each other but make no actual progress

Deadlock

:   two or more threads are blocked forever, waiting for each other.

Critical Section

:   Piece of code that belongs to a thread that shares state with other
    threads and needs to be synchronised

    We only want one thread to be in a critical section at a time.

Mutual Exclusion

:   Only one thread to be in a critical section at a time.

Reentrant Synchronization

:   Allowing a thread to acquire the same lock more than once.

## References

-   [Java Concurrency
    Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)
-   [Lock
    interface](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Lock.html)
-   [Overview of concurrency in
    Java](http://www.vogella.com/tutorials/JavaConcurrency/article.html)
-   [Runnable
    interface](https://docs.oracle.com/javase/10/docs/api/java/lang/Runnable.html)
-   [Thread
    class](https://docs.oracle.com/javase/10/docs/api/java/lang/Thread.html)
-   [atomic vs volatile vs
    synchronised](https://stackoverflow.com/questions/9749746/what-is-the-difference-between-atomic-volatile-synchronized)
-   [concurrency
    package](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/package-summary.html)
-   [locks
    package](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/locks/package-summary.html)
