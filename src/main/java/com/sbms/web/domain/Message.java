package com.sbms.web.domain;

public class Message {
    private String language;

    private String content;

    public Message() {
        super();
    }

    public Message(String language, String content) {
        this.language = language;
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
