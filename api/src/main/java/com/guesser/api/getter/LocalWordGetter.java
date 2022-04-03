package com.guesser.api.getter;

import com.guesser.api.model.word.Word;
import com.guesser.api.utils.InputStreamUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LocalWordGetter implements WordGetter {

    private static final String fileName = "all_english_words.txt";
    private final List<String> allWords = getAllWordsInFile(fileName);

    @Override
    public Word getRandomWord() throws IOException {
        String randomWord = getARandomWord(allWords);
        return new Word(randomWord);
    }

    @Override
    public Word getRandomWordOfLengthN(int length) {
        List<String> allWordsOfLengthN = getAllWordsOfLengthN(allWords, length);
        String randomWord = getARandomWord(allWordsOfLengthN);
        return new Word(randomWord);
    }

    @Override
    public Word getRandomFullWord() {
        return null;
    }

    private List<String> getAllWordsOfLengthN(List<String> allWords, int length) {
        return allWords.stream()
            .filter(word -> word.length() == length)
            .collect(Collectors.toList());
    }

    private List<String> getAllWordsInFile(String fileName) {
        InputStream is = convertFileToInputStream(fileName);

        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.toList());
    }

    private InputStream convertFileToInputStream(String fileName) {
        return InputStreamUtils.getFileFromResourceAsStream(fileName);
    }

    private String getARandomWord(List<String> words) {
        int numberOfWords = words.size();
        try {
            int randomIndexOfWord = new Random().nextInt(numberOfWords);
            return words.get(randomIndexOfWord);
        } catch (Exception e) {
            return "ERROR_IN_GETTING_WORD";
        }
    }

    public List<String> getAllWords() {
        return allWords;
    }
}
