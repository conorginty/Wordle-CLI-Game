package com.wordle.game;

import com.guesser.api.getter.LocalWordGetter;
import com.guesser.api.getter.WordGetter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Wordle {

    private final int MAX_NUMBER_OF_GUESSES = 6;
    private List<String> guessedWords = new ArrayList<>();

    public void playGame() {
        System.out.println("You are playing Wordle!");
        String actualWord = getRandomFiveLetterWordInUpperCase();
        System.out.println(actualWord);
        boolean correct = false;

        while (guessedWords.size() < MAX_NUMBER_OF_GUESSES) {
            String guess = getUserGuess();
            System.out.println("Your guess is: " + guess);
            String result = analyseGuess(guess, actualWord);
            System.out.println(result);
            if (guess.equals(actualWord)) {
                correct = true;
                guessedWords.add(guess);
                break;
            } else {
                System.out.println(guess + " is not the Wordle word");
                guessedWords.add(guess);
                System.out.println("Number of guesses made: " + guessedWords.size());
                printGuesses();
            }
        }

        if (correct) {
            System.out.println("WELL DONE! You got it right - the Wordle word was: " + actualWord);
            System.out.println("Total number of guesses: " + guessedWords.size());
            printGuesses();
        } else {
            System.out.println("Unfortunately you have run out of guesses...");
            System.out.println("The correct word was: " + actualWord);
            System.out.println("Please try again next time!");
        }
    }

    private void printGuesses() {
        System.out.println("Your guesses: " + guessedWords.toString());
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
