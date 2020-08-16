package com.tts.entities;

import java.util.ArrayList;
import java.util.List;

public class Tweet1 {
    private int Id;
    private String userName;
    private String message;
    ArrayList<Tweet1> list1;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Tweet1> getList1() {
        return list1;
    }

    public void setList1(ArrayList<Tweet1> list1) {
        this.list1 = list1;
    }

    public Tweet1(int id, String userName, String message) {
        Id = id;
        this.userName = userName;
        this.message = message;
    }
    @Override
    public String toString() {
        return "Tweet{" +
                "userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
