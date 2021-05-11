# Context-free grammar processor
This program processes a context-free grammar in three steps:

1. Checking if the language of the grammar is empty ("Empty check");
3. Eliminating chain rules from the grammar ("Chain rules elimination");
4. Converting the grammar to Chomsky normal form ("Chomsky normal form convertation").

## Restrictions on the input expression
Input sets of terminals or nonterminals can consists only of letters and digits.  
Start symbol should belong to set of nonterminals.  
Symbol 'e' means "empty regexp".  
(When you start the program, you can see an example).  

## Example of input
Start symbol = S  
N = {S, A, B}  
∑ = {a, b}  
P = {S->aAB|BA, A->BBB|a, B->AS|b}  

## Result
![alt text](example2.gif "Example")
