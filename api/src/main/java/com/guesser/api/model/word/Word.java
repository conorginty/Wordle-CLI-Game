package com.guesser.api.model.word;

import java.util.List;

public class Word {
    private String word;
    private PartOfSpeech partOfSpeech;
    private List<String> definitions;
    private List<String> synonyms;
    private List<String> antonyms;
    private Language language;
    private Difficulty difficulty;
    private String hint;
    private String example;

    public Word(String word) {
        this.word = word;
    }

    public Word(String word, List<String> definitions) {
        this.word = word;
        this.definitions = definitions;
    }

    public Word(String word, PartOfSpeech partOfSpeech, List<String> definitions, List<String> synonyms, List<String> antonyms, String example) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definitions = definitions;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
        this.example = example;
    }

    public Word(String word, PartOfSpeech partOfSpeech, List<String> definitions, List<String> synonyms,
                List<String> antonyms, Language language, Difficulty difficulty, String hint, String example) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definitions = definitions;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
        this.language = language;
        this.difficulty = difficulty;
        this.hint = hint;
        this.example = example;
    }

    public int length() {
        return this.word.length();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public String getExample() {
        return example;
    }

    public String getExampleWithWordHidden() {
        String hiddenWord = new String(new char[this.word.length()]).replace("\0", "-");
        String hiddenExample = this.example.replaceAll("(?i)"+this.word, hiddenWord);
        System.out.println("Hidden Example: " + hiddenExample);
        return hiddenExample;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "Word{" +
            "word='" + word + '\'' +
            ", partOfSpeech=" + partOfSpeech +
            ", definitions=" + definitions +
            ", synonyms=" + synonyms +
            ", antonyms=" + antonyms +
            ", example='" + example + '\'' +
            '}';
    }
}
