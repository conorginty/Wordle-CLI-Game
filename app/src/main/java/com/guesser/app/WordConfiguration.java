package com.guesser.app;

import com.guesser.api.getter.LocalWordGetter;
import com.guesser.api.getter.RemoteWordGetter;
import com.guesser.api.scraper.WordScraper;
import com.guesser.api.service.WordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordConfiguration {
    @Bean
    public WordService wordService(){
        try {
            return new WordService(new LocalWordGetter());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Local word-getting service not working. Using remote word-getting service instead");
            return new WordService(new RemoteWordGetter(new WordScraper()));
        }
    }
}
