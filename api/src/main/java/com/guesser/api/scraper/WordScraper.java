package com.guesser.api.scraper;

import com.guesser.api.getter.LocalWordGetter;
import com.guesser.api.getter.WordGetter;
import com.guesser.api.model.word.PartOfSpeech;
import com.guesser.api.model.word.Word;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class WordScraper implements WebScraper<String> {

    public static final String HTTPS_RANDOMWORD_COM = "https://randomword.com/";

    public String scrapeRandomWord() throws IOException {
        String ret;
        try {
            ret = getWordFromJson("https://random-word-api.herokuapp.com/word?number=1&swear=0");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Trying to get word from another API instead...");
            ret = getWordFromHtml(HTTPS_RANDOMWORD_COM);
        }
        return ret;
    }

    private String getWordFromJson(String url) {
        String jsonText = parseJsonFromUrl(url);
        return getWordFromJsonArray(jsonText);
    }

    private String parseJsonFromUrl(String url) {
        String jsonText = null;
        try (InputStream is = new URL(url).openStream();) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
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
        return html.select("div#random_word").text();
    }

    public Word scrapeRandomWordAndDefinitions() {
        Word ret = null;
        String url = HTTPS_RANDOMWORD_COM;
        try {
            ret = getWordAndDefinitionsFromHtml(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to scrape word and definition from API...");
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
                System.out.println("Word or some attribute of the word does not exist in the Dictionary API. Trying again with a new random word...");
                url = newDictionaryApiUrlWithRandomWord();
            }
        }
        return ret;
    }

    private String newDictionaryApiUrlWithRandomWord() throws IOException {
        WordGetter localWordGetter = new LocalWordGetter();
        String randomWord = localWordGetter.getRandomWord().getWord();
        return "https://api.dictionaryapi.dev/api/v2/entries/en/" + randomWord;
    }

    private Word getFullWordFromJson(String url) {
        String json = parseJsonFromUrl(url);

        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(json);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("JSON text is null");
        }

        JSONObject jsonObject = (JSONObject) jsonArray.get(0);

        String word = jsonObject.getString("word");
        System.out.println("The word is: " + word);
        JSONObject meanings = (JSONObject) jsonObject.getJSONArray("meanings").get(0);

        String partOfSpeechString = meanings.getString("partOfSpeech");
        PartOfSpeech partOfSpeech;
        try {
            partOfSpeech = PartOfSpeech.valueOf(partOfSpeechString.toUpperCase());
        } catch (IllegalArgumentException e) {
            partOfSpeech = PartOfSpeech.UNKNOWN;
        }
        System.out.println("The partOfSpeech is: " + partOfSpeech);

        JSONArray synonymsJsonArray = meanings.getJSONArray("synonyms");
        List<String> synonyms = convertJsonArrayOfStringsToArrayList(synonymsJsonArray);
        System.out.println("The synonyms are: " + synonyms);

        JSONArray antonymsJsonArray = meanings.getJSONArray("antonyms");
        List<String> antonyms = convertJsonArrayOfStringsToArrayList(antonymsJsonArray);
        System.out.println("The antonyms are: " + antonyms);

        JSONArray definitions = meanings.getJSONArray("definitions");
        String definition = ((JSONObject) definitions.get(0)).getString("definition");
        System.out.println("The definition is: " + definition);

        String example;
        try {
            example = ((JSONObject) definitions.get(0)).getString("example");
            System.out.println("An example is: " + example);
        } catch (Exception e) {
            System.out.println("No example available for: " + word);
            example = "No example exists for word";
        }
        return new Word(word, partOfSpeech, List.of(definition), synonyms, antonyms, example);
    }

    private List<String> convertJsonArrayOfStringsToArrayList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (Object word: jsonArray) {
            list.add(word.toString());
        }
        return list;
    }
}
