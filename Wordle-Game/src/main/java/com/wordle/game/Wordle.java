package com.wordle.game;

import com.guesser.api.getter.LocalWordGetter;
import com.guesser.api.getter.WordGetter;

public class Wordle {
    public void playGame() {
        System.out.println("You are playing Wordle!");
        String actualWord = getRandomFiveLetterWordInUpperCase();
        System.out.println(actualWord);
    }

    public String getRandomFiveLetterWordInUpperCase() {
        WordGetter wordGetter = new LocalWordGetter();
        String randomWord = wordGetter.getRandomWordOfLengthN(5).getWord();
        return randomWord.toUpperCase();
    }

}
