package com.guesser.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordController {

    @GetMapping("/")
    public String wordPage() {
        return "word";
    }
}
