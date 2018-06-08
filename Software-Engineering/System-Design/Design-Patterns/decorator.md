# Decorator

![UML class diagram](./decorator.png)

## Classification

-   object-oriented

## Problem it Solves

-   allows us to add functionality to objects dynamically
-   can be a substitute for inheritance
    -   especially if inheritance in our case would lead to a very nested
        class hierarchy

## Components

1.  Shared interface
    -   define relevant methods e.g. `void draw();`
2.  The core
    -   concrete class
    -   implements the interface
3.  The wrapper (i.e. decorator)
    -   is abstract
    -   *has* the core
    -   implements the interface
4.  Wrapper subclasses
    -   concrete classes
    -   implement the interface
    -   override the methods
    -   delegate to the base decorator class (`super.draw();`) and then execute their own extra code

## Example

1.  Create a "lowest common denominator" that makes classes interchangeable
2.  Create a second level base class for optional functionality
3.  "Core" class and "Decorator" class declare an "isa" relationship
4.  Decorator class "has a" instance of the "lowest common denominator"
5.  Decorator class delegates to the "has a" object
6.  Create a Decorator derived class for each optional embellishment
7.  Decorator derived classes delegate to base class AND add extra stuff
8.  Client has the responsibility to compose desired configurations

``` {.java}
// 1. "lowest common denominator"
interface Widget {
    void draw();
}

// 3. "Core" class with "is a" relationship
class TextField implements Widget {
        private int width, height;

        public TextField(int width, int height) {
            this.width = width;
            this.height = height;
        }
        public void draw() {
            System.out.println("TextField: " + width + ", " + height);
        }
}

// 2. Second level base class with "isa" relationship
abstract class Decorator implements Widget {
        // 4. "has a" relationship
        private Widget widget;

        public Decorator(Widget widget) {
            this.widget = widget;
        }

        // 5. Delegation
        public void draw() {
            widget.draw();
        }
}

// 6. Optional embellishment
class BorderDecorator extends Decorator {
        public BorderDecorator(Widget widget) {
            super(widget);
        }
        public void draw() {
            // 7. Delegate to base class and add extra stuff
            super.draw();
            System.out.println("  BorderDecorator");
        }
}

// 6. Optional embellishment
class ScrollDecorator extends Decorator {
        public ScrollDecorator(Widget widget) {
            super(widget);
        }
        public void draw() {
            super.draw(); // 7. Delegate to base class and add extra stuff
            System.out.println("  ScrollDecorator");
        }
}

public class DecoratorDemo {
        public static void main(String[] args) {
            // 8. Client has the responsibility to compose desired configurations
            Widget widget = new BorderDecorator(new BorderDecorator(new ScrollDecorator(
                                                    new TextField(80, 24))));
            widget.draw();
        }
}
```


## Notes

-   allows behavior to be added to an individual object, either statically or
    dynamically, without affecting the behavior of other objects from the same
    class.

-   The decorator pattern is often useful for adhering to the **Single Responsibility Principle**, as it allows functionality to be divided between classes with unique areas of concern.

-   structurally nearly identical to the chain of responsibility pattern, the
    difference being that in a chain of responsibility, exactly one of the
    classes handles the request, while for the decorator, all classes handle
    the request.
