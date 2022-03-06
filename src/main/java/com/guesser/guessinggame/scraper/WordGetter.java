package com.guesser.guessinggame.scraper;

import com.guesser.guessinggame.model.word.Word;

import java.io.IOException;

public class WordGetter {

    private WordScraper wordScraper;

    public WordGetter(WordScraper wordScraper) {
        this.wordScraper = wordScraper;
    }

    public Word getRandomWord() throws IOException {
        String word = this.wordScraper.scrapeRandomWord();
        return new Word(word);
    }

    public Word getRandomWordAndDefinition() throws IOException {
        Word word = this.wordScraper.scrapeRandomWordAndDefinitions();
        return word;
    }
}