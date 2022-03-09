package com.guesser.guessinggame.getter;

import com.guesser.guessinggame.model.word.Word;

import java.io.IOException;

public interface WordGetter {
    Word getRandomWord() throws IOException;
}
