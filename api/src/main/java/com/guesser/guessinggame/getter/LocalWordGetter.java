package com.guesser.guessinggame.getter;

import com.guesser.guessinggame.model.word.Word;
import com.guesser.guessinggame.utils.InputStreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LocalWordGetter implements WordGetter {

    @Override
    public Word getRandomWord() throws IOException {
        String fileName = "all_english_words.txt";
        List<String> allWords = getAllWords(fileName);

        String randomWord = getARandomWord(allWords);
        return new Word(randomWord);
    }

    private List<String> getAllWords(String fileName) {
        InputStream is = convertFileToInputStream(fileName);

        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.toList());
    }

    private InputStream convertFileToInputStream(String fileName) {
        return InputStreamUtils.getFileFromResourceAsStream(fileName);
    }

    private String getARandomWord(List<String> allWords) {
        int numberOfWords = allWords.size();
        int randomIndexOfWord = new Random().nextInt(numberOfWords);
        return allWords.get(randomIndexOfWord);
    }
}
