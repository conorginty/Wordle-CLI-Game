package com.wordle.game;

public class LetterState {
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_GREY = "\u001B[90m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final char letter;
    private final boolean isInWord;
    private final boolean isInPosition;
    private String state;

    public LetterState(char letter, boolean isInWord, boolean isInPosition) {
        this.letter = letter;
        this.isInWord = isInWord;
        this.isInPosition = isInPosition;
    }

    private void updateState() {
        if (isInWord && isInPosition) {
            this.state = ANSI_GREEN + letter + ANSI_RESET;
        } else if (isInWord) {
            this.state = ANSI_YELLOW + letter + ANSI_RESET;
        } else {
            this.state = ANSI_GREY + letter + ANSI_RESET;
        }
    }

    public String getState() {
        updateState();
        return state;
    }

    public char getLetter() {
        return letter;
    }
}
