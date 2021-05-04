# Trie Java Implementation

This is a simple Java program to demonstrate how to create a Trie dictionary 
for the following tasks:

- add a word
- check if a word is present or not
- delete a word
- check for word suggestions given a prefix
- check all the words present in the trie 

## Output

```shell script
Adding word 'Sunny'.
Adding word 'Srinidhi' recursively.
------------------------------
Word 'Sunny' present: true
Word 'Srinidhi' present: true
------------------------------
Deleting word 'Sunny'
Word 'Sunny' present: false
Word 'Srinidhi' present: true
------------------------------
Adding word 'Sunny' recursively.
Adding word 'Sun' recursively.
Adding word 'Sunny1' recursively.
------------------------------
Suggestions for prefix 'Su': [Sun, Sunny, Sunny1]
------------------------------
All words present: [Srinidhi, Sun, Sunny, Sunny1]
------------------------------
```