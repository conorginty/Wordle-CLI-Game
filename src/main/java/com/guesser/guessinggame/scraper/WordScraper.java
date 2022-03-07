package com.guesser.guessinggame.scraper;

import com.guesser.guessinggame.model.word.Word;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONArray;

public class WordScraper implements WebScraper {

    public String scrapeRandomWord() throws IOException {
        String ret;
        try {
            ret = getWordFromJson("https://random-word-api.herokuapp.com/word?number=1&swear=0");
        } catch (Exception e) {
            e.printStackTrace();
            ret = getWordFromHtml("https://randomword.com/");
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

    private String getWordFromHtml(String url) throws IOException {
        Document html = Jsoup.connect(url).get();
        String word = html.select("div#random_word").text();
        return word;
    }

    public Word scrapeRandomWordAndDefinitions() throws IOException {
        Word ret = null;
        String url = "https://randomword.com/";
        try {
            ret = getWordAndDefinitionsFromHtml(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private Word getWordAndDefinitionsFromHtml(String url) throws IOException {
        Document html = Jsoup.connect(url).get();
        String word = html.select("div#random_word").text();
        String definition = html.select("div#random_word_definition").text();
        return new Word(word, List.of(definition));
    }

    public Word scrapeFullWord() throws IOException {
        Word ret = null;
        String url = newDictionaryApiUrlWithRandomWord();

        boolean unsuccessful = true;
        while (unsuccessful) {
            try {
                ret = getFullWordFromJson(url);
                unsuccessful = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Word does not exist in the Dictionary API. Trying again with a new random word...");
                url = newDictionaryApiUrlWithRandomWord();
            }
        }
        return ret;
    }

    private String newDictionaryApiUrlWithRandomWord() throws IOException {
        String randomWord = getWordFromHtml("https://randomword.com/");
        return "https://api.dictionaryapi.dev/api/v2/entries/en/" + randomWord;
    }

    private Word getFullWordFromJson(String url) {
        String json = parseJsonFromUrl(url);
        JSONArray jsonArray = new JSONArray(json);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);

        String word = jsonObject.getString("word");
        JSONObject meanings = (JSONObject) jsonObject.getJSONArray("meanings").get(0);
        System.out.println("The word is: " + word);
        JSONArray definitions = meanings.getJSONArray("definitions");
        String definition = ((JSONObject) definitions.get(0)).getString("definition");
        System.out.println("The definition is: " + definition);
        return new Word(word, List.of(definition));
    }
}
