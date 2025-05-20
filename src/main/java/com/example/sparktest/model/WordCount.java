package com.example.sparktest.model;

import java.io.Serializable;

public class WordCount implements Serializable {
    private static final long serialVersionUID = 1L;
    private String word;
    private int count;

    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }
} 