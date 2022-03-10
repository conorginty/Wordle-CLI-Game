package com.guesser.api.service;

import com.guesser.api.model.word.Word;

import java.io.IOException;

public interface WordService {
    Word getRandomWord() throws IOException;

    Word getRandomWordOfLengthN(int length);

    Word getRandomFullWord() throws IOException;
}
