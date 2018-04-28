# Composite

-   Compose objects into tree-like structures.
-   "Directories contain entries, each of which could be a directory."
-   Let A be the parent of B and C where B is a primitive and C stores As.

## When to Use

-   recursive data structure
-   AST
-   file system (files and directories)
-   you have some hierarchy where a composite object (i.e. a node) is treated
    differently than a primitive object (i.e. a leaf).
-   you don't want to query the type (`instanceof`) before carrying out each
    operation (see below for what composite avoids)

``` {.java}
class Directory extends FSNode {

    private FSNode nodes;

    ...

    List<String> list() {

        return nodes.stream().map(x -> {

            Collection result = new LinkedList();

            if (x instanceof File) 
                result.add(file);

            else if (x instanceof Directory) 
                result.add(x.list());

            else if (x instanceof Pipe)  {
                result.add(new Pipe(null))
            }

            ...

        });
}
```

## Impelemtnation

1.  Common interface
2.  Primitive type
3.  Composite type (contains objects of that implement the common interfaces)

## Example

``` {.java}
interface FSNode<E> {
    E list();
}

class File implements FSNode<String> {

    public String list() {
        return this.fname;
    }
}

class Directory implements FSNode<List<String>> {

    private Collection<FSNode<List<String>>> dirs;
    private Collection<FSNode<String>> files;

    public List<String> list() {

        return new List(this.files).extend(
            dirs.stream()
                .map(FSNode<List<String>>::list)
                .collect(Collectors.toList()));
    }
}
```

## Notes

- a visitor can apply the right operation over a composite depending on it's
  type
