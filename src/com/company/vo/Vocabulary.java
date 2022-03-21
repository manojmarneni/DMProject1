package com.company.vo;

import java.util.HashMap;
import java.util.Map;

public class Vocabulary {
    int numberOfWords;
    Map<String, Integer> wordIndex;

    public Vocabulary() {
        this.wordIndex = new HashMap<>();
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public Map<String, Integer> getWordIndex() {
        return wordIndex;
    }

    public void setWordIndex(Map<String, Integer> wordIndex) {
        this.wordIndex = wordIndex;
    }
}
