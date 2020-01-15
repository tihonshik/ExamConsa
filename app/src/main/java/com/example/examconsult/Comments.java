package com.example.examconsult;

import java.util.Date;

public class Comments {

    private int id;
    private String author_id;

    private String comment;
    private String date;


    public Comments(String comment, String date, String author_id) {
        this.comment = comment;
        this.date = date;
        this.author_id = author_id;
    }

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }
}
