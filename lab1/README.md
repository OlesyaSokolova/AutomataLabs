# AutomataLabs
This program builds a finite state machine based on a regular expression that is fed to the input. 
The result is shown as Moore diagram.

## Restrictions on the input expression
Input regexp can consists only of letters, digits and symbols of operations described below.
Symbol 'e' means "empty regexp".

## Acceptable operations
1. Concatenation: ab (no symbol for this operation)
2. Disjunction: a+b
3. Iteration: a*

## Result
### Legend
1. q0 - start state
2. q1 - end state
3. Start state and end state are indicated in turquoise, other states - in lilac.

### Editing
You can drag the vertices as you like.

