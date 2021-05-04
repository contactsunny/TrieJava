package com.contactsunny.poc.triejava;

import java.util.List;

public class App {

    public static void main(String[] args) {
        Node root = new Node();

        System.out.println("Adding word 'Sunny'.");
        root.insert("Sunny");

        System.out.println("Adding word 'Srinidhi' recursively.");
        root.insertRecursively("Srinidhi");

        System.out.println("------------------------------");

        System.out.println("Word 'Sunny' present: " + root.search("Sunny"));
        System.out.println("Word 'Srinidhi' present: " + root.searchRecursively("Srinidhi"));

        System.out.println("------------------------------");

        System.out.println("Deleting word 'Sunny'");
        root.delete("Sunny");
        System.out.println("Word 'Sunny' present: " + root.search("Sunny"));
        System.out.println("Word 'Srinidhi' present: " + root.searchRecursively("Srinidhi"));

        System.out.println("------------------------------");

        System.out.println("Adding word 'Sunny' recursively.");
        root.insertRecursively("Sunny");

        System.out.println("Adding word 'Sun' recursively.");
        root.insertRecursively("Sun");

        System.out.println("Adding word 'Sunny1' recursively.");
        root.insertRecursively("Sunny1");

        System.out.println("------------------------------");

        List<String> suggestions = root.getSuggestions("Su");
        System.out.println("Suggestions for prefix 'Su': " + suggestions);

        System.out.println("------------------------------");

        List<String> allWords = root.getSuggestions("");
        System.out.println("All words present: " + allWords);

        System.out.println("------------------------------");
    }
}
