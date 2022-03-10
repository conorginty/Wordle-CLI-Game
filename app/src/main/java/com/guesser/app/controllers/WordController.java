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

    // I DON'T UNDERSTAND HOW THE CONTROLLER KNOWS HOW TO DISTINGUISH BETWEEN THE 2 IMPLEMENTATIONS
    // OF WORD SERVICE HERE WITHOUT THE USE OF QUALIFIERS????
    // I DON'T THINK IT'S BECAUSE THEIR NAMES MATCHES THE METHODS USED TO CREATE THE BEANS...
    private final WordService localWordService;
    private final WordService remoteWordService;

    public WordController(WordService localWordService, WordService remoteWordService) {
        this.localWordService = localWordService;
        this.remoteWordService = remoteWordService;
    }

    @GetMapping("/")
    public String wordPage(Model model) throws IOException {
        Word randomWord = this.remoteWordService.getRandomFullWord();
        model.addAttribute("randomWord", randomWord);
        System.out.println(randomWord);
        return "word";
    }

    @RequestMapping("/{length}")
    public String wordOfSizeNPage(@PathVariable(value = "length") int length, Model model) {
        Word randomWord = this.localWordService.getRandomWordOfLengthN(length);
        model.addAttribute("randomWord", randomWord);
        System.out.println(randomWord);
        return "wordOfLengthN";
    }
}
