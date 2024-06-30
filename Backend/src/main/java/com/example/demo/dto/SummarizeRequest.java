package com.example.demo.dto;

public class SummarizeRequest {
    private String text;

    // Default constructor
    public SummarizeRequest() {
    }

    // Constructor with parameters
    public SummarizeRequest(String text) {
        this.text = text;
    }

    // Getter and setter methods
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
