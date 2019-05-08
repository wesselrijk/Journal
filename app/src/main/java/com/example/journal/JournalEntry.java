package com.example.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    private int id;
    private String title;
    private String content;
    private String mood;
    private Long timestamp;

    // Constructor of the class
    public JournalEntry(int id, String title, String content, String mood) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = System.currentTimeMillis();
    }

    // list of getters TODO: remove unnecessary getters at the end of coding process
    public int getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getContent() {return this.content; }
    public String getMood() { return this.mood; }
    public Long getTimestamp() { return this.timestamp; }

    // list of setters  TODO: remove unnecessary setters at the end of coding process
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setMood(String mood) { this.mood = mood; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
}
