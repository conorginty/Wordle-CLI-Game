package com.guesser.guessinggame;

import com.guesser.guessinggame.model.word.Word;
import com.guesser.guessinggame.scraper.WordGetter;
import com.guesser.guessinggame.scraper.WordScraper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WordScraper wordScraper = new WordScraper();
        WordGetter wordGetter = new WordGetter(wordScraper);

        try {
            Word randomWord = wordGetter.getRandomWord();
            System.out.println(randomWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
