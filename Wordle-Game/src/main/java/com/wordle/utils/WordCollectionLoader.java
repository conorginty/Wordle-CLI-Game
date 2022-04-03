package com.wordle.utils;

import com.guesser.api.getter.LocalWordGetter;

import java.util.Collection;

public class WordCollectionLoader {
    public static void loadWithWordsOfLengthN(Collection<String> wordCollection, int n) {
        LocalWordGetter wordGetter = new LocalWordGetter();
        Collection<String> allWords = wordGetter.getAllWords();

        allWords.stream()
            .filter(word -> word.length() == n)
            .forEachOrdered(wordCollection::add);
    }
}
