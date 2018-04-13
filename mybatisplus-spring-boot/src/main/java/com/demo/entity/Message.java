package com.demo.entity;


import lombok.Data;

@Data
public class Message {
    private String title;
    private String content;
    private String result;

    public Message(String title, String content, String result) {
        super();
        this.title = title;
        this.content = content;
        this.result = result;
    }
}
