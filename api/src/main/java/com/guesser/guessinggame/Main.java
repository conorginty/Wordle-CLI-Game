package com.guesser.guessinggame;

import com.guesser.guessinggame.model.word.Word;
import com.guesser.guessinggame.scraper.LocalWordGetter;
import com.guesser.guessinggame.scraper.WordGetter;
import com.guesser.guessinggame.scraper.WordScraper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WordScraper wordScraper = new WordScraper();
        WordGetter wordGetter = new WordGetter(wordScraper);

        try {
            System.out.println("=== Get Random Word ===");
            Word randomWord = wordGetter.getRandomWord();
            System.out.println(randomWord);

            System.out.println("=== Get Random Word and Definition ===");
            Word randomWordAndDefinition = wordGetter.getRandomWordAndDefinition();
            System.out.println(randomWordAndDefinition);

            System.out.println("=== Get 'Full' Random Word ===");
            Word fullWord = wordGetter.getFullRandomWord();
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
