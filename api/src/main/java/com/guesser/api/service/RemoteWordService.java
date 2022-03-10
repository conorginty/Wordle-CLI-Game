package com.guesser.api.service;

import com.guesser.api.getter.RemoteWordGetter;
import com.guesser.api.model.word.Word;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RemoteWordService implements WordService {
    private final RemoteWordGetter wordGetter;

    public RemoteWordService(RemoteWordGetter wordGetter) {
        this.wordGetter = wordGetter;
    }

    @Override
    public Word getRandomWord() throws IOException {
        return wordGetter.getRandomWord();
    }

    @Override
    public Word getRandomWordOfLengthN(int length) {
        return wordGetter.getRandomWordOfLengthN(length);
    }

    @Override
    public Word getRandomFullWord() throws IOException {
        return wordGetter.getRandomFullWord();
    }
}
