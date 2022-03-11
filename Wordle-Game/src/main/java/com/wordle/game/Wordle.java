package com.wordle.game;

import com.guesser.api.getter.LocalWordGetter;
import com.guesser.api.getter.WordGetter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Wordle {
    public void playGame() {
        System.out.println("You are playing Wordle!");
        String actualWord = getRandomFiveLetterWordInUpperCase();
        System.out.println(actualWord);

        String guess = getUserGuess();
        System.out.println("Your guess is: " + guess);

        String result = analyseGuess(guess, actualWord);
        System.out.println(result);
    }

    private String analyseGuess(String guess, String actual) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            char currentGuessChar = guess.charAt(i);
            char matchingActualChar = actual.charAt(i);

            boolean guessCharIsInActualWord = actual.indexOf(currentGuessChar) >= 0;
            boolean guessCharIsInSamePositionAsActualWord = currentGuessChar == matchingActualChar;

            LetterState letterState = new LetterState(currentGuessChar, guessCharIsInActualWord, guessCharIsInSamePositionAsActualWord);
            sb.append(letterState.getState());
        }
        return sb.toString();
    }

    private String getUserGuess() {
        String guess = null;

        boolean successful = false;

        while (!successful) {
            Scanner reader = new Scanner(System.in);
            try {
                System.out.println("Enter a 5 letter word: ");
                String input = reader.nextLine();
                successful = validateGuess(input);
                guess = input.toUpperCase();
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
            // TODO close the reader after getting valid guess
        }
        return guess;
    }

    public String getRandomFiveLetterWordInUpperCase() {
        WordGetter wordGetter = new LocalWordGetter();
        String randomWord = wordGetter.getRandomWordOfLengthN(5).getWord();
        return randomWord.toUpperCase();
    }

    private boolean validateGuess(String guess) {
        return isFiveLettersLong(guess) && onlyContainsLetters(guess);
    }

    private boolean isFiveLettersLong(String guess) {
        if (guess.length() == 5) {
            return true;
        }
        System.out.println("Word must be 5 letters long");
        return false;
    }

    private boolean onlyContainsLetters(String guess) {
        if (guess.matches("[a-zA-Z]+")) {
            return true;
        }
        System.out.println("Word must only contain letters");
        return false;
    }
}
