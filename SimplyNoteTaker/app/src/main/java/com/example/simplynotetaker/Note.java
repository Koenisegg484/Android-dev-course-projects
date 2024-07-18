package com.example.simplynotetaker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.type.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public String today;

    public static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.today = LocalDateTime.now().format(formatter);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getToday() {
        return today;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
