package com.example.assignment_2;

// user class
public class User {

    // create username
    private String username;

    // create password
    private String password;

    // create favorite color
    private String favoriteColor;

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public String getPassword(String username) {
        return password;
    };
}
