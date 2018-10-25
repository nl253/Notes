# Go 

- go initialises uninitialised vars to a "zero value" (e.g.: 0 for ints)
- `make(...)` is used to create "reference types"
- in the case of primitives such as ints assignment copies the values
- reference types are really just pointers (i.e. addresses) to objects on the heap
- go syntax
    - `select` similar to "choice" i.e. `+` in CCS
    - `peek`

## Select (Choice) in Go

- similar to switch statement

```go
select {
case <-a:
        fmt.Print("a")
case <-b:
        fmt.Print("b")
}
```

- if something was sent on `a` then the `b` branch is discard
- if nothing is sent on either, then you will be stuck waiting until something
  is sent
- so if >1 is runnable then one will be chosen at random (fairly i.e. with
  a probability to be chosen equally distributed)




