package com.guesser.api.service;

import com.guesser.api.getter.WordGetter;
import com.guesser.api.model.word.Word;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WordService {

    private final WordGetter wordGetter;

    public WordService(WordGetter wordGetter) {
        this.wordGetter = wordGetter;
    }

    public Word getRandomWord() throws IOException {
        return wordGetter.getRandomWord();
    }

    public Word getRandomWordOfLengthN(int length) {
        return wordGetter.getRandomWordOfLengthN(length);
    }
}
