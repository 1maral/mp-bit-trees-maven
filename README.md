# mp-bit-trees-maven

A mini-project exploring bit trees (a form of binary tree) and their use in translating between alphabets, particularly in translating to and from braille.

**Authors**

* Maral Bat-Erdene
* Samuel A. Rebelsky (starter code)

**Acknowledgements**

* Samuel A. Rebelsky: helped me print in unicode during class.

**Instructions for use**

This program allows you to easily convert text between three formats: regular text called "ASCII", Braille bit strings of 0s and 1s called "braille", and Unicode Braille symbols called "unicode". Simply input the desired format and the string to be converted, and the program will handle the translation for you. For example, you can convert the word "hello" into Braille or Unicode, or translate Braille back into readable text. The program expects the input to be valid, but provides clear error messages for incorrect lengths. To use this program, run the BrailleASCII class located in the main folder and provide two command-line inputs: the target format and the string to convert.

For example:
user input: java -cp target/classes edu.grinnell.csc207.main.BrailleASCII braille hello  
expected output: 110010100010111000111000101010

user input: java -cp target/classes edu.grinnell.csc207.main.BrailleASCII unicode "hello world"  
expected output: ⠓⠑⠇⠇⠕⠀⠺⠕⠗⠇⠙

---

This code may be found at <https://github.com/1maral/mp-bit-trees-maven.git>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-bit-trees-maven>.
