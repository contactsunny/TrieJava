package com.contactsunny.poc.triejava;

import java.util.*;

public class Node {

    private final Map<Character, Node> children = new HashMap<Character, Node>();
    private boolean endOfWord = false;

    /**
     * Method to insert a word into the Trie.
     *
     * @param word The word to insert.
     */
    public void insert(String word) {
        // The current node is the root node.
        Node current = this;
        // Iterating over all the characters in the word to add them one by one.
        for (int index = 0; index < word.length(); index++) {
            // Getting the next character to add.
            char character = word.charAt(index);
            // Getting the node for the character
            Node node = current.children.get(character);
            // Checking if there is already a node for the character.
            // If not, creating it, adding it to the map.
            if (node == null) {
                node = new Node();
                current.children.put(character, node);
            }
            // Thc current node will be changed to the node of the character.
            current = current.children.get(character);
        }
        // Once the loop is complete, it means we have reached the end of the word.
        // So setting the flag at this node.
        current.endOfWord = true;
    }

    /**
     * Method to start the recursive addition of a word.
     *
     * @param word The word to be added.
     */
    public void insertRecursively(String word) {
        insertRecursively(word, 0, this);
    }

    /**
     * Method to insert a word recursively. The logic is same as the iterative approach.
     *
     * @param word The word to insert.
     * @param index The index of the character we're working with.
     * @param node The next node where we have to add the next character.
     */
    public void insertRecursively(String word, int index, Node node) {
        // If we have reached the end of the word, we'll set the flag and return.
        if (index == word.length()) {
            node.endOfWord = true;
            return;
        }
        // The character to add.
        char character = word.charAt(index);
        // Getting the node for the character
        Node child = node.children.get(character);
        // Checking if there is already a node for the character.
        // If not, creating it, adding it to the map.
        if (child == null) {
            child = new Node();
            node.children.put(character, child);
        }
        // Calling the method again to insert the next character at the next node.
        // Thc current node will be changed to the node of the character.
        insertRecursively(word, index + 1, child);
    }

    /**
     * Method to search for a word and return a boolean based on the result.
     *
     * @param word The word to search
     * @return true if the word is found, false if not.
     */
    public boolean search(String word) {
        // Starting the search from the root node.
        Node current = this;
        // Iterating over the length of the word to find nodes one character at a time.
        for (int index = 0; index < word.length(); index++) {
            // The character to search.
            char character = word.charAt(index);
            // Getting the node for the character.
            Node node = current.children.get(character);
            // If the node is null, that means we don't have the character.
            // Which in turn means we don't have the word. So returning a false.
            if (node == null) {
                return false;
            }
            // If we found the node, it means we can proceed with the next character.
            // So the current pointer will be moved to the node we found.
            current = node;
        }
        // After the loop, we'll be at the last node for the string given.
        // If the flag here is set, it means that it's a complete word and we've found it.
        // But if the flag is reset, it means that we don't have the word we're searching for.
        return current.endOfWord;
    }

    /**
     * Method to initiate a word search recursively.
     *
     * @param word The word to search.
     * @return true if the word is found, false if not.
     */
    public boolean searchRecursively(String word) {
        return searchRecursively(word, 0, this);
    }

    /**
     * Method to search a word using recursion. The logic is similar to the iterative approach.
     *
     * @param word The word to search
     * @param index The index at which we're performing the search
     * @param node The current node.
     * @return true if the word is found, false if not.
     */
    public boolean searchRecursively(String word, int index, Node node) {
        // Making sure we're not going beyond the length of the word.
        if (index < word.length()) {
            // The character to search
            char character = word.charAt(index);
            // Node for the character.
            Node child = node.children.get(character);
            // If the node is null, it means we don't have the word we're searching for.
            // So returning false.
            if (child == null) {
                return false;
            }
            // If we found the node, it means we can proceed with the next character.
            // So calling the method again with the next index and the next node.
            return searchRecursively(word, index + 1, child);
        }
        // After the loop, we'll be at the last node for the string given.
        // If the flag here is set, it means that it's a complete word and we've found it.
        // But if the flag is reset, it means that we don't have the word we're searching for.
        return node.endOfWord;
    }

    /**
     * Method to call the delete method recursively.
     *
     * @param word The word to delete.
     */
    public void delete(String word) {
        delete(word, 0, this);
    }

    /**
     * Method to delete a word.
     *
     * @param word The word to delete
     * @param index The index at which the node has to be deleted
     * @param node The current node.
     * @return true if the parent node has to be deleted.
     */
    public boolean delete(String word, int index, Node node) {
        // If we're at the last index, it means we've found the word we're trying to delete.
        if (index == word.length()) {
            // But it may be a prefix for a longer word.
            // So if it's not the end of a word, we'll not delete the parent node.
            // So returning a false here.
            if (!node.endOfWord) {
                return false;
            }
            // But because this is not the end of any word anymore,
            // Resetting this flag.
            node.endOfWord = false;
            // If there's no child anymore, the parent node can be deleted.
            // Making that decision here.
            return node.children.size() == 0;
        }
        // The character that we're going to delete.
        char character = word.charAt(index);
        // The node for the character.
        Node child = node.children.get(character);
        // If the node is null, we don't have the word we're trying to delete.
        // So the parent should not be deleted. Hence returning a false.
        if (child == null) {
            return false;
        }
        // Trying to figure out if the parent has to be deleted, which is the current node.
        boolean shouldDeleteParent = delete(word, index + 1, child);
        // If it is to be deleted,
        // removing it from the children array
        if (shouldDeleteParent) {
            node.children.remove(character);
            // We also have to delete the next parent if there are no children anymore.
            // Chaecking that here.
            return node.children.size() == 0;
        }
        // If nothing, don't delete anything.
        return false;
    }

    /**
     * Methods to get the list of suggestions for the provided prefix.
     *
     * @param prefix The prefix for suggestions.
     *
     * @return List of suggestions.
     */
    public List<String> getSuggestions(String prefix) {
        // Creating a list for storing all the suggestions.
        List<String> suggestions = new ArrayList<String>();
        // Getting the node at which the prefix ends, as we have to start
        // looking for words from that node.
        Node node = searchNode(prefix, 0, this);
        // If the node is null, it means we don't have any word with the given prefix.
        // So checking for that. If not null, calling the method
        // to add another character to the suggestion.
        if (node != null) {
            getSuggestions(prefix, node, suggestions);
        }
        // Returning all the suggestions that we have till now.
        return suggestions;
    }

    /**
     * Method to search for the node at which the given prefix ends.
     *
     * @param prefix The given prefix
     * @param index The next index to search
     * @param node The next node to search
     *
     * @return Node if we find it, null if not.
     */
    private Node searchNode(String prefix, int index, Node node) {
        // Making sure that we don't run out of bounds
        if (index < prefix.length()) {
            // Character to search for at the current index
            char character = prefix.charAt(index);
            // Getting the node for the given character
            Node child = node.children.get(character);
            // If the child is null, it means we didn't find any node for the prefix
            // So returning a null.
            if (child == null) {
                return null;
            }
            // Continuing to search for the next node for the next character.
            return searchNode(prefix, index + 1, child);
        }
        // Returning the node as we have reached the end of the prefix and found the node.
        return node;
    }

    /**
     * Method to get the list of suggestions for the given prefix from the given node.
     *
     * @param prefix The given prefix
     * @param node The node at which the prefix ends.
     * @param suggestions The list of suggestions we have till now.
     */
    public void getSuggestions(String prefix, Node node, List<String> suggestions) {
        // If the current node is marked as the end of a word,
        // it means we have a word to add to the suggestions.
        // So adding the prefix, which has the complete word, to the list.
        if (node.endOfWord) {
            suggestions.add(prefix);
        }
        // Checking if we have more children at this node to check if we have more words to add.
        // Iterating over the list of children to check for more words.
        for (Map.Entry<Character, Node> characterNodeEntry : node.children.entrySet()) {
            // The next character to search for.
            char character = characterNodeEntry.getKey();
            // Calling the method again with the next character to get the suggestions.
            // Adding the character at the current node to the prefix to keep forming the words.
            getSuggestions(prefix + character, node.children.get(character), suggestions);
        }
    }
}
