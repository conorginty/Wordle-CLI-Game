package com.wordle.game;

import com.guesser.api.getter.LocalWordGetter;
import com.guesser.api.getter.WordGetter;
import com.wordle.utils.WordCollectionLoader;

import java.util.*;

public class Wordle {

    public static final String PLEASE_TRY_AGAIN = "Please try again";
    private final int MAX_NUMBER_OF_GUESSES = 6;
    private static final int WORD_LENGTH = 5;
    private List<String> guessedWords = new ArrayList<>();
    WordGetter wordGetter = new LocalWordGetter();
    private static final List<String> allWordsOfSize_WORD_LENGTH = new ArrayList<>();

    static {
        WordCollectionLoader.loadWithWordsOfLengthN(allWordsOfSize_WORD_LENGTH, WORD_LENGTH);
    }

    public void playGame() {
        System.out.println("You are playing Wordle!");
        String actualWord = getRandomWordOfSize_WORD_LENGTH_InUpperCase();
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
            System.out.println(PLEASE_TRY_AGAIN + " next time!");
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
                System.out.println("Enter a " + WORD_LENGTH + " letter word: ");
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

    public String getRandomWordOfSize_WORD_LENGTH_InUpperCase() {
        String randomWord = wordGetter.getRandomWordOfLengthN(WORD_LENGTH).getWord();
        return randomWord.toUpperCase();
    }

    private boolean validateGuess(String guess) {
        return is_WORD_LENGTH_LettersLong(guess) && onlyContainsLetters(guess)
            && isInListOfValidWords(guess) && hasNotBeenGuessedPreviously(guess);
    }

    private boolean is_WORD_LENGTH_LettersLong(String guess) {
        if (guess.length() == WORD_LENGTH) {
            return true;
        }
        System.out.println("Word must be " + WORD_LENGTH + " letters long. " + PLEASE_TRY_AGAIN);
        return false;
    }

    private boolean onlyContainsLetters(String guess) {
        if (guess.matches("[a-zA-Z]+")) {
            return true;
        }
        System.out.println("Word must only contain letters. " + PLEASE_TRY_AGAIN);
        return false;
    }

    private boolean isInListOfValidWords(String guess) {
        if (allWordsOfSize_WORD_LENGTH.contains(guess.toLowerCase())) {
            return true;
        }
        System.out.println(guess + " is not a valid word. " + PLEASE_TRY_AGAIN);
        return false;
    }

    private boolean hasNotBeenGuessedPreviously(String guess) {
        if (guessedWords.contains(guess)) {
            System.out.println("You have already tried the word: " + guess + ". " + PLEASE_TRY_AGAIN);
            return false;
        }
        return true;
    }
}
