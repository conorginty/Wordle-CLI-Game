package com.guesser.api.scraper;

import java.io.IOException;

public interface WebScraper<T> {
    T scrapeRandomWord() throws IOException;

    // TODO
    // T scrapeWord(String word, String url) throws IOException;
}
