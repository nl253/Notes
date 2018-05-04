# Builder

Split the construction of a complex object into multiple function calls.
At the end run `build()` to create it.

## Classification

-   creational
-   object oriented

## Problem It Solves

- Some objects require more components to 'build' them.
- You want to avoid constructors with >10 arguments.
- You want to delay the creation of an object.

## Example

-   `StringBuilder` -- use `append()` and later run `toString()`
-   `StreamBuilder`

```java
class HouseBuilder {
    
    /* many fields */

    public HouseBuilder setHouseName(int houseName) {
        this.houseName = houseName;
        return this;
    }

    public HouseBuilder setHouseHeight(int houseHeight) {
        this.houseHeight = houseHeight;
        return this;
    }

    public HouseBuilder setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
        return this;
    }

    public HouseBuilder setHouseWidth(int houseWidth) {
        this.houseWidth = houseWidth;
        return this;
    }

    public HouseBuilder setHouseSize(int houseSize) {
        this.houseSize = houseSize;
        return this;
    }

    /* other setters for other fields */

    public House create() {
        return new House(this.houseName, this.houseAddress, this.houseSize, this.houseWidth, this.houseHeight, ... );
    }
}

public static void main(String... args) {

    House house = new HouseBuilder()
                        .setHouseName("MyHouse")
                        .setHouseHeight(123)
                        .setHouseWidth(123)
                        .setHouseAddress("Address of the house")
                        .create();
}
```
