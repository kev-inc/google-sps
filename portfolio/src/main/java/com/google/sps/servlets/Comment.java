package com.google.sps.servlets;

public class Comment {
    private String message;
    private Double score;
    public Comment(String message, Double score) {
        this.message = message;
        this.score = score;
    }

    public String getMessage() {
        return this.message;
    }

    public Double getSentiment() {
        return this.score;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSentiment(Double score) {
        this.score = score;
    }
}
