# All things I didn't know about Javascript

## Classes

### Prototypes

```javascript
class Point {
    constructor(x = 0, y = 100) {
        this.x = x;
        this.y = y;
    }

    getX() {
        return this.x;
    }

    getY() {
        return this.y;
    }
}

let p = new Point();

console.log(p); // Point { x: 0, y: 100 }

console.log(p.getX()); // 0
console.log(p.getY()); // 100

// Now modify it ...

let tmp = p.__proto__.getX;
p.__proto__.getX = p.__proto__.getY;
p.__proto__.getY = tmp;

console.log(p.getX()); // 100
console.log(p.getY()); // 0
// REVERSED 
```

### Constructors

- Are just any functions
- Not necessarily inside a class
- All that's required is that the function is called *as* constructor

E.g.:

```javascript
function FunnyBusiness() {
    let obj = {};
    obj.funny = 'sure';
    return obj;
}

let x = new FunnyBusiness(); 
console.log(x.funny); // sure
```
Constructor function or prototype specifies an initial set of
properties. Can add or remove properties dynamically to individual
objects or to the entire set of objects.

### Getters 

- Define a function that will be automatically called when a property
  is looked up.
- Hide the fact that a function is called to compute a value
- Possibly use less space and compute properties on the fly when
  requested rather than save them
- Minimise data redundancy

```javascript
class Car {
    constructor(yearProduced) {
        this.yearProduced = yearProduced;
    }

    get age() {
       return (new Date()).getFullYear() - this.yearProduced;
    }
}

let c = new Car(2001);
console.log(c.age); // 17
```

## Functions

### `this`{.javascript}

- depends on where it was called from
- depends also on the following special functions present in `Function.prototype`:
    - `apply(thisObj, arrayOfArgs)`{.javascript}
    - `call(thisObj, arg1, arg2, ...)`{.javascript}
    - `bind(thisObj)`{.javascript} returns a function where references
      to `this`{.javascript} are replaced with `thisObj`{.javascript}

### `arguments`{.javascript}

When in a function you can always reference the arguments object which is an
iterable object.

## Objects

- check if an object has a property (think key) using `x in obj`{.javascript}

### Promise

### Iterables


#### Set

- because it's an object we can use `set.values()`{.javascript} to get an iterator

#### WeakSet

#### Map

#### WeakMap

#### Generator

- declare with an `*` as in `function* () { ... }`{.javascript}
- function that allows to be iterated over
- taken from Python
- special `yield`{.javascript} keyword to return next item and pause
- good if you don't want to execute all at once
- good if you are dealing with infinite sequences
- you can call `iterFunc.next()`{.javascript} on the function to
  produce next value
- when called, it runs the code until `yield`{.javascript} is reached,
  then it pauses execution and returns the result of
  `yield ... ;`{.javascript}

```javascript
function* fib() {
    let fst = 0;
    let snd = 1;
    while(true) {
        yield snd;
        let tmp = snd;
        snd = fst + tmp;
        fst = tmp; 
    }
}

let fibIter = fib();

console.log(fibIter.next()); // { value: 1, done: false }
console.log(fibIter.next()); // { value: 1, done: false }
console.log(fibIter.next()); // { value: 2, done: false }
```

#### Arrays

- are objects
- are iterable i.e. you can iterate through all properties (i.e. indices) AND
  values
- properties are indices
- in modern JavaScript you can initialise with:
  - `new Array()`{.javascript}
  - `Array()`{.javascript}
  - `Array.of()`{.javascript}
  - `[]`{.javascript}

  Function                     Signature                                                                       Side Effect
  ---------------------------- ------------------------------------------------------------------------------- -------------------------------------------------------------------------------------
  `fill`{.javascript}          `[a] -> b -> [b]`{.haskell}                                                     modifies the array and returns it 
  `fill`{.javascript}          `[a] -> Int -> b -> [a/b] OR [a] -> Int -> Int -> b -> [a/b]`{.haskell}         modifies the array and returns it 
  `fill`{.javascript}          `[a] -> Int -> Int -> b -> [a/b]`{.haskell}                                     modifies the array and returns it 
  `pop`{.javascript}           `[a] -> a`{.haskell}                                                            as a side effect removes a from [a], returns the removed item
  `push`{.javascript}          `[a] -> a -> [a]`{.haskell}                                                     as a side effect adds a to [a], returns the new item
  `shift`{.javascript}         `[a] -> [a] as side effect returns a`{.haskell}                                 modifies the array be removing the left-most element, returns the item
  `splice`{.javascript}        `[a] -> Word -> [a]`{.haskell}                                                  modifies the array, returns array of deleted items
  `splice`{.javascript}        `[a] -> Word -> Word -> [a]`{.haskell}                                          modifies the array, returns array of deleted items
  `splice`{.javascript}        `[a] -> Word -> Word -> a[, a, ...] -> [a]`{.haskell}                           modifies the array, returns array of deleted items
  `unshift`{.javascript}       `[a] -> a[, a, ...] -> [a]`{.haskell}                                           modifies the array by prepending a~1~, a~2~, ..., actually returns size
  `sort`{.javascript}          `[a] -> [a]`{.haskell}                                                          modifies the original array, returns it 
  `reverse`{.javascript}       `[a] -> [a]`{.haskell}                                                          modifies the original array, returns it 
  `forEach`{.javascript}       `[a] -> (a -> b) -> b`{.haskell}                                                --

  Pure Function                Signature
  ---------------------------- -----------------------------------------------------------------------
  `concat`{.javascript}        `[a] -> [a] -> [a]`{.haskell}                                                  
  `copyWithin`{.javascript}    --                                                                             
  `entries`{.javascript}       `[a] -> Iterator Word a`{.haskell}                                             
  `every`{.javascript}         `[a] -> (a -> Boolean) -> Boolean`{.haskell}                                   
  `filter`{.javascript}        `[a] -> (a -> Boolean) -> [a]`{.haskell}                                       
  `find`{.javascript}          `[a] -> (a -> Boolean) -> a`{.haskell}                                         
  `findIndex`{.javascript}     `[a] -> (a -> Boolean) -> Word`{.haskell}                                      
  `includes`{.javascript}      `[a] -> a -> Boolean`{.haskell}                                                
  `indexOf`{.javascript}       `[a] -> a -> Int`{.haskell}                                                    
  `join`{.javascript}          `[a] -> String -> String`{.haskell}                                            
  `keys`{.javascript}          `[a] -> Iterator Word`{.haskell}                                               
  `lastIndexOf`{.javascript}   `[a] -> a -> Int`{.haskell}                                                    
  `length`{.javascript}        `[a] -> Int`{.haskell}                                                         
  `map`{.javascript}           `[a] -> (a -> b) -> [b]`{.haskell}                                             
  `reduce`{.javascript}        `[a] -> (a -> b -> b) -> b OR [a] -> a -> (a -> b -> b) -> b`{.haskell}        
  `reduceRight`{.javascript}   `[a] -> (a -> b -> b) -> b OR [a] -> a -> (a -> b -> b) -> b`{.haskell}        
  `slice`{.javascript}         `[a] -> Word -> [a] OR [a] -> Word -> Word -> [a]`{.haskell}                   
  `some`{.javascript}          `[a] -> (a -> Boolean) -> Boolean`{.haskell}                                   

##### Gotachas

You can actually assign to `array.length`{.javascript} as in `xs.length = 2`{.javascript} which modifies it's contents.

E.g.:

```javascript
let xs = [1,2,3];
xs.length = 0;
console.log(xs); // []
xs.length = 3; 
console.log(xs); // [ <3 empty items> ]
```

Arrays are indexed but JavaScript with allow you to assign arbitrary
properties to arrays (because arrays are objects). E.g.:

```javascript
let xs = [];
xs[0] = 2; // OK
xs['yes'] = 'OK'; 
console.log(xs.yes) // OK
```

JavaScript will allow you to assign `xs[2] = 100`{.javascript} to an
empty array `[]`{.javascript}. E.g.:

```javascript
let xs = [];
xs[2] = 100; // OK
console.log(xs) // [ <2 empty items>, 100 ]
```

#### `for ... in ...`{.javascript}

The `for ... in`{.javascript} statement iterates a specified variable
over all the **enumerable properties** of an object. For each distinct
property, JavaScript executes the specified statements.

```javascript
for (let i in object) {
  // statements
}
```

**NOTE**: You cannot use it for all object since not every object is iterable.
A good use case are arrays which *are* objects and contain properties (the indices).

E.g.:

```javascript
let xs = [1, 2, 3];

for (let property in xs) {
  console.info(`value: ${xs[property]}, property (aka index): ${property}`);
}

// value: 1, property (aka index): 0
// value: 2, property (aka index): 1
// value: 3, property (aka index): 2
```

#### `for ... of ...`{.javascript}

Instead of iterating through properties, iterate through values.
E.g.:

```javascript
let xs = [1, 2, 3];
let i = 0;

for (let val of xs) {
  console.info(`value: ${val}, property (aka index): ${i}`);
  i++;
}

// value: 1, property (aka index): 0
// value: 2, property (aka index): 1
// value: 3, property (aka index): 2

```

See warning below about iteratability.

##### Implementing Iteration 

```javascript
let funnyObject = {};

funnyObject['yes'] = 'no';
funnyObject['no'] = 'maybe';
funnyObject['maybe'] = 'yes';

funnyObject[Symbol.iterator] = function*() {
    while(true) {
        yield this.yes;
        yield this.no;
        yield this.maybe;
    }
};

let i = 0;
let limit = 5;

for (let phrase of funnyObject) {
    if (i === limit) break;
    console.log(phrase);
    i++;
}

// no
// maybe
// yes
// no
// maybe
```

###### Yield all from aka `yield*`{.javascript}

```javascript
function* odds() {
    let start = 1;
    while(true) {
        yield start;
        start += 2;
    }
}

function* evens() {
    let start = 0;
    while(true) {
        yield start;
        start += 2;
    }
}

function* fakeOdds() {
    let o = odds();
    yield o.next();
    yield o.next();
    yield o.next();
    yield* evens();
}

let fake = fakeOdds();

console.log(fake.next())
console.log(fake.next())
console.log(fake.next())
console.log(fake.next())
console.log(fake.next())

// { value: { value: 1, done: false }, done: false }
// { value: { value: 3, done: false }, done: false }
// { value: { value: 5, done: false }, done: false }
// { value: 0, done: false }
// { value: 2, done: false }
```
