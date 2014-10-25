Spellchecker
============

Spellcheck program. Parses a given file into individual word Strings, then checks it against a main dictionary ArrayList. 

Classes-

Book: Responsible for loading text files and parsing them by paragraph.

Dictionary: Loads in a dictionary file, parses by word, sorts alphabetically, and removes duplicates.

SpellChecker: Checks the words in a Book object against the dictionary using a binary search, adds any words not found in the dictionary to a Collection. This class also handles displaying the book text, the dictionary words, and the misspelled words. 
