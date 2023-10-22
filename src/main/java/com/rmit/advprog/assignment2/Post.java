package com.rmit.advprog.assignment2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Post {
    private SimpleIntegerProperty id;
    private SimpleStringProperty content;
    private SimpleStringProperty author;
    private SimpleIntegerProperty likes;
    private SimpleIntegerProperty shares;
    private SimpleStringProperty date_time;

    // Constructor
    public Post(int id, String content, String author, int likes, int shares, String date_time) {
        this.id = new SimpleIntegerProperty(id);
        this.content = new SimpleStringProperty(content);
        this.author = new SimpleStringProperty(author);
        this.likes = new SimpleIntegerProperty(likes);
        this.shares = new SimpleIntegerProperty(shares);
        this.date_time = new SimpleStringProperty(date_time);
    }

    // Getters
    public int getId() {
        return id.get();
    }

    public String getContent() {
        return content.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public int getLikes() {
        return likes.get();
    }

    public int getShares() {
        return shares.get();
    }

    public String getDate_time() {
        return date_time.get();
    }

    // Properties
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public SimpleIntegerProperty likesProperty() {
        return likes;
    }

    public SimpleIntegerProperty sharesProperty() {
        return shares;
    }

    public SimpleStringProperty date_timeProperty() {
        return date_time;
    }


    //String
    @Override
    public String toString() {
        return "Post: {ID: " + id.get() + ", Content: " + content.get() + ", Author: " + author.get() + ", Likes: " + likes.get() + ", Shares: " + shares.get() + ", Date_time: " + date_time.get() + "}";
    }
}