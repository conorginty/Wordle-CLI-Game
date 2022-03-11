package com.wordle.game;

public class LetterState {
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_GREY = "\u001B[90m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final char letter;
    private final boolean isInWord;
    private final boolean isInPosition;

    public LetterState(char letter, boolean isInWord, boolean isInPosition) {
        this.letter = letter;
        this.isInWord = isInWord;
        this.isInPosition = isInPosition;
    }

    public String getState() {
        if (isInWord && isInPosition) {
            return ANSI_GREEN + letter + ANSI_RESET;
        } else if (isInWord) {
            return ANSI_YELLOW + letter + ANSI_RESET;
        } else {
            return ANSI_GREY + letter + ANSI_RESET;
        }
    }
}
