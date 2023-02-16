package com.example.firebase;
public class chat {
    String username;
    String message;

    public chat(){
    }

    public chat(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
