package com.example.journal;
/**
 * The JournalEntry class for the app.
 * This is the class that holds the information for any journal entry. This includes the title,
 * the content, the mood that has been set and the timestamp at which time the entry was created.
 */

import java.io.Serializable;

public class JournalEntry implements Serializable {

    private String title;
    private String content;
    private String mood;
    private Long timestamp;

    // Constructor of the class sets initialized variables.
    public JournalEntry(String title, String content, String mood, Long timestamp) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    // List of getters.
    public String getTitle() { return this.title; }
    public String getContent() {return this.content; }
    public String getMood() { return this.mood; }
    public Long getTimestamp() { return this.timestamp; }
}
