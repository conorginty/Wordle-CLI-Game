package com.guesser.app.controllers;

import com.guesser.api.model.word.Word;
import com.guesser.api.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String wordPage(Model model) throws IOException {
        Word randomWord = wordService.getRandomFullWord();
        model.addAttribute("randomWord", randomWord);
        System.out.println(randomWord);
        return "word";
    }

    @RequestMapping("/{length}")
    public String wordOfSizeNPage(@PathVariable(value = "length") int length, Model model) {
        Word randomWord = wordService.getRandomWordOfLengthN(length);
        model.addAttribute("randomWord", randomWord);
        System.out.println(randomWord);
        return "wordOfLengthN";
    }
}
