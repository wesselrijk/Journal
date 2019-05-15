package com.example.journal;

// (list of) import(s) used
import java.io.Serializable;

public class JournalEntry implements Serializable {

    // instantiate necessary variables
    private String title;
    private String content;
    private String mood;
    private Long timestamp;

    // Constructor of the class
    public JournalEntry(String title, String content, String mood, Long timestamp) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    // list of getters
    public String getTitle() { return this.title; }
    public String getContent() {return this.content; }
    public String getMood() { return this.mood; }
    public Long getTimestamp() { return this.timestamp; }
}
