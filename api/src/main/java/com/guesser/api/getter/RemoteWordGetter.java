package com.guesser.api.getter;

import com.guesser.api.model.word.Word;
import com.guesser.api.scraper.WordScraper;

import java.io.IOException;

public class RemoteWordGetter implements WordGetter {

    private WordScraper wordScraper;

    public RemoteWordGetter(WordScraper wordScraper) {
        this.wordScraper = wordScraper;
    }

    @Override
    public Word getRandomWord() throws IOException {
        String word = this.wordScraper.scrapeRandomWord();
        return new Word(word);
    }

    @Override
    public Word getRandomWordOfLengthN(int length) {
        return null;
    }

    public Word getRandomWordAndDefinition() throws IOException {
        Word word = this.wordScraper.scrapeRandomWordAndDefinitions();
        return word;
    }

    public Word getFullRandomWord() throws IOException {
        Word word = this.wordScraper.scrapeFullWord();
        return word;
    }
}