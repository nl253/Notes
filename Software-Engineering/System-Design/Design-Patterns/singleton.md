# Singleton

-   You need a single instance of some class to be globally visible.
-   You want to prevent making more than one instances.
-   You need global access to a set of utility methods.

## Implementation

-   private constructor
-   `static E getInstance()` method as the only way to get an object of this
    class

## Example

``` {.java}
public class Database {

    private Database self;

    private Database() {}

    static Database getInstance() {
        if (self == null) self = new Database();
        return self;
    }
}
```

## Evaluation

-   might be convenient in many cases where a global access to some set of
    utility methods is needed
-   global accessibility is criticised
-   the need for a singleton probably demonstrates bad design

## Notes

-   abstract factory might make use of this design pattern
