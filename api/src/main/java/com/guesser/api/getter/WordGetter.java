package com.guesser.api.getter;

import com.guesser.api.model.word.Word;

import java.io.IOException;

public interface WordGetter {
    Word getRandomWord() throws IOException;
}
