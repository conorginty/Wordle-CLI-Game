package com.guesser.guessinggame.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;

public class WordScraper implements WebScraper {

    public String scrapeRandomWord() {
        String ret = null;
        try {
            ret = getWordFromJson("https://random-word-api.herokuapp.com/word?number=1&swear=0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private String getWordFromJson(String url) {
        String jsonText = parseJsonFromUrl(url);
        String word = getWordFromJsonArray(jsonText);
        return word;
    }

    private String parseJsonFromUrl(String url) {
        String jsonText = null;
        try (InputStream is = new URL(url).openStream();) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            jsonText = readAll(bufferedReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    private String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private String getWordFromJsonArray(String jsonText) {
        if (jsonText == null) {
            throw new IllegalArgumentException("JSON text is null");
        }
        JSONArray jsonArray = new JSONArray(jsonText);
        return jsonArray.getString(0);
    }
}
