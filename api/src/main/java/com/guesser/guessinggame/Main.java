package com.guesser.guessinggame;

import com.guesser.guessinggame.model.word.Word;
import com.guesser.guessinggame.getter.LocalWordGetter;
import com.guesser.guessinggame.getter.RemoteWordGetter;
import com.guesser.guessinggame.scraper.WordScraper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WordScraper wordScraper = new WordScraper();
        RemoteWordGetter remoteWordGetter = new RemoteWordGetter(wordScraper);

        try {
            System.out.println("=== Get Random Remote Word ===");
            Word randomWord = remoteWordGetter.getRandomWord();
            System.out.println(randomWord);

            System.out.println("=== Get Random Remote Word and Definition ===");
            Word randomWordAndDefinition = remoteWordGetter.getRandomWordAndDefinition();
            System.out.println(randomWordAndDefinition);

            System.out.println("=== Get 'Full' Remote Random Word ===");
            Word fullWord = remoteWordGetter.getFullRandomWord();
            System.out.println(fullWord);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalWordGetter localWordGetter = new LocalWordGetter();

        try {
            System.out.println("=== Get Random Local Word ===");
            Word randomWord = localWordGetter.getRandomWord();
            System.out.println(randomWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
