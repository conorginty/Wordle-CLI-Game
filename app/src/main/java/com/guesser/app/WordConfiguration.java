package com.guesser.app;

import com.guesser.api.getter.LocalWordGetter;
import com.guesser.api.getter.RemoteWordGetter;
import com.guesser.api.scraper.WordScraper;
import com.guesser.api.service.LocalWordService;
import com.guesser.api.service.RemoteWordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordConfiguration {
    @Bean
    public LocalWordService localWordService(){
        return new LocalWordService(new LocalWordGetter());
    }

    @Bean
    public RemoteWordService remoteWordService(){
        return new RemoteWordService(new RemoteWordGetter(new WordScraper()));
    }
}
