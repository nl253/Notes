1. Implement a function variables :: Expr a -> [String] which collects the variables seen in
a Expr expression value into a list. For example:
*Main> variables example
["a","b"]

2. Implement a function inEnv :: [(String, a)] -> String -> Bool which checks whether a
variable (the second parameter) is in an environment (the first parameter), e.g.
*Main> inEnv exampleEnv "c"
False
*Main> inEnv exampleEnv "a"
True

3. Using variables and inEnv, implement a function validEnv :: [(String, a)] -> Expr a -> Bool
which checks whether an environment provides a definition for every variable in an expression.
Try doing without writing your own custom recursive function. (Hint: use map and use Hoogle
to find a library function for taking the conjunction of a list of bools to produce a single bool).
Example values:
*Main> validEnv exampleEnv example
True
*Main> validEnv [] example
False
The Expr data type models syntax trees of numerical expressions. Imagine we are implementing a
language with assignment statements, and input and output statements. Here is a sample program
which asks the user for two inputs, adds them, and prints the output:
x = input
y = input
z = x + y
output z
We define a new data type representing statements in this language:
1
data Stmt = Assign String (Expr Int) | Out (Expr Int) | In String

4. Write an instance of Show for Stmt which produce code string like in the above syntax example.

5. A program is a list of statements:
1 data Program = Program [Stmt]
Write an instance of Show for Program which outputs each statement on a new line.

6. Define a value of the Program data type representing the above sample program.

7. Define an interpreter that takes an environment and a list of input integers which are consumed
by In statements, producing a list of output integersproduced by Out statements, i.e., of the
type:
1
eval :: [(String, Int)] -> [Int] -> Program -> [Int]
2
