# Command

![](./command.png)

-   A way of storing a function as an object in object-oriented programming
    languages.
-   Promote a function to an object.
-   You want to be able to choose or transport a function but defer it's
    execution.
-   You want to pass a function form a sender to receiver in object-oriented
    languages such as Java.

## Example

``` {.java}
interface Command<E> {
    E execute();
}

class DomesticEngineer implements Command {
    public E execute() {
        return System.out.println("take out the trash");
    }
}

class Politician implements Command {
    public E execute() {
        return System.out.println("take money from the rich, take votes from the poor");
    }
}

class Programmer implements Command {
    public E execute() {
        return System.out.println("sell the bugs, charge extra for the fixes");
    }
}
```
